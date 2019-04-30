package cis410.onlinebanking.controller;

import cis410.onlinebanking.ResponseObject;
import cis410.onlinebanking.entities.BankAccount;
import cis410.onlinebanking.sent.*;
import cis410.onlinebanking.service.BankAccountService;
import cis410.onlinebanking.service.OnlineAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class BankingController {
    private final static Logger log = Logger.getLogger(BankingController.class.getName());

    private final OnlineAccountService onlineAccountService;
    private final BankAccountService bankAccountService;

    @Autowired
    public BankingController(OnlineAccountService accountService, BankAccountService bankAccountService) {
        this.onlineAccountService = accountService;
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@RequestBody LoginAttempt attempt, HttpServletResponse res){
        if(onlineAccountService.verifyAccount(attempt)){
            res.setStatus(200);
        }
        else{
            res.setStatus(401);
        }
    }

    @PostMapping("/createOnlineAccount")
    @ResponseBody
    public ResponseObject createOnlineAccount(@RequestBody NewOnlineAccount onlineAccount){
        return onlineAccountService.createOnlineAccount(onlineAccount);
    }

    @PostMapping("/transfer")
    @ResponseBody
    public ResponseObject transferMoney(@RequestBody TransferRequest transferRequest){
        return bankAccountService.transfer(transferRequest);
    }

    @PostMapping("/deposit")
    @ResponseBody
    public ResponseObject depositMoney(@RequestBody DepositRequest depositRequest){
        return bankAccountService.depositMoney(depositRequest);
    }

    @PostMapping("/getUserAccounts")
    @ResponseBody
    public List<BankAccount> getUserAccounts(@RequestBody StringWrapper userName){
        log.info("NAME: " + userName.getWrappedString());
        return bankAccountService.getUserAccounts(userName.getWrappedString());
    }
}
