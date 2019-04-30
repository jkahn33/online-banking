package cis410.onlinebanking.service;

import cis410.onlinebanking.ResponseObject;
import cis410.onlinebanking.dao.BankAccountDAO;
import cis410.onlinebanking.dao.CustomerDAO;
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

    public synchronized ResponseObject transfer(TransferRequest transferRequest){
        Optional<OnlineAccount> onlineAccountOptional = onlineAccountDAO.findById(transferRequest.getUser());
        if(!onlineAccountOptional.isPresent()){
            return new ResponseObject(false, "User doesn't exist.");
        }
        AccountType fromType = null;
        switch(transferRequest.getFrom()){
            case 1:
                fromType = AccountType.CHECKING;
                break;
            case 2:
                fromType = AccountType.SAVINGS;
                break;
        }
        AccountType toType = null;
        switch(transferRequest.getTo()){
            case 1:
                toType = AccountType.CHECKING;
                break;
            case 2:
                toType = AccountType.SAVINGS;
                break;
        }
        Optional<BankAccount> bankAccountOptional = bankAccountDAO.findByCustomerAndType(onlineAccountOptional.get().getCustomer(), fromType);
        if(!bankAccountOptional.isPresent()){
            return new ResponseObject(false, "From Account doesn't exist.");
        }
        BankAccount from = bankAccountOptional.get();

        bankAccountOptional = bankAccountDAO.findByCustomerAndType(onlineAccountOptional.get().getCustomer(), toType);
        if(!bankAccountOptional.isPresent()){
            return new ResponseObject(false, "To Account doesn't exist.");
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
        Optional<OnlineAccount> onlineAccountOptional = onlineAccountDAO.findById(depositRequest.getUser());
        if(!onlineAccountOptional.isPresent()){
            return new ResponseObject(false, "User doesn't exist.");
        }

        AccountType type = null;
        switch(depositRequest.getTarget()){
            case 1:
                type = AccountType.CHECKING;
                break;
            case 2:
                type = AccountType.SAVINGS;
                break;
        }
        Optional<BankAccount> bankAccountOptional = bankAccountDAO.findByCustomerAndType(onlineAccountOptional.get().getCustomer(), type);
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
