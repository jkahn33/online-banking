package cis410.onlinebanking.service;

import cis410.onlinebanking.ResponseObject;
import cis410.onlinebanking.dao.BankAccountDAO;
import cis410.onlinebanking.dao.OnlineAccountDAO;
import cis410.onlinebanking.entities.AccountType;
import cis410.onlinebanking.entities.BankAccount;
import cis410.onlinebanking.entities.Customer;
import cis410.onlinebanking.entities.OnlineAccount;
import cis410.onlinebanking.sent.DepositRequest;
import cis410.onlinebanking.sent.NewBankAccount;
import cis410.onlinebanking.sent.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private final OnlineAccountDAO onlineAccountDAO;
    private final BankAccountDAO bankAccountDAO;

    @Autowired
    public BankAccountService(OnlineAccountDAO onlineAccountDAO, BankAccountDAO bankAccountDAO) {
        this.onlineAccountDAO = onlineAccountDAO;
        this.bankAccountDAO = bankAccountDAO;
    }

    public void createBankAccount(NewBankAccount bankAccount){
        AccountType type = null;
        switch (bankAccount.getType()){
            case 1:
                type = AccountType.CHECKING;
                break;
            case 2:
                type = AccountType.SAVINGS;
                break;
        }
        Optional<OnlineAccount> onlineAccountOptional = onlineAccountDAO.findById(bankAccount.getCustomerId());
        BankAccount account = new BankAccount(onlineAccountOptional.get().getCustomer(), type, (float)0.0);

        bankAccountDAO.save(account);
    }

    public synchronized ResponseObject transfer(TransferRequest transferRequest){
        Optional<BankAccount> bankAccountOptional = bankAccountDAO.findById(transferRequest.getFrom());
        if(!bankAccountOptional.isPresent()){
            return new ResponseObject(false, "Bank Account doesn't exist.");
        }
        BankAccount from = bankAccountOptional.get();

        bankAccountOptional = bankAccountDAO.findById(transferRequest.getTo());
        if(!bankAccountOptional.isPresent()){
            return new ResponseObject(false, "Bank Account doesn't exist.");
        }
        BankAccount to = bankAccountOptional.get();

        if(from.getBalance() - transferRequest.getAmount() < 0){
            return  new ResponseObject(false, "Insufficient funds attempted to transfer.");
        }
        from.setBalance(from.getBalance() - transferRequest.getAmount());
        to.setBalance(to.getBalance() + transferRequest.getAmount());

        bankAccountDAO.save(from);
        bankAccountDAO.save(to);

        return new ResponseObject(true, "Successfully transferred money.");
    }

    public synchronized ResponseObject depositMoney(DepositRequest depositRequest){
        Optional<BankAccount> bankAccountOptional = bankAccountDAO.findById(depositRequest.getAccount());
        if(!bankAccountOptional.isPresent()){
            return new ResponseObject(false, "Bank Account doesn't exist.");
        }
        BankAccount bankAccount = bankAccountOptional.get();
        bankAccount.setBalance(bankAccount.getBalance() + depositRequest.getAmount());

        bankAccountDAO.save(bankAccount);

        return new ResponseObject(true, "Successfully deposited money.");
    }

    public List<BankAccount> getUserAccounts(String userId){
        Optional<OnlineAccount> onlineAccountOptional = onlineAccountDAO.findById(userId);
        if(!onlineAccountOptional.isPresent()){
            return null;
        }
        OnlineAccount onlineAccount = onlineAccountOptional.get();
        Customer customer = onlineAccount.getCustomer();

        return bankAccountDAO.findAllByCustomer(customer);
    }
}
