package cis410.onlinebanking.controller;

import cis410.onlinebanking.ResponseObject;
import cis410.onlinebanking.sent.LoginAttempt;
import cis410.onlinebanking.sent.NewBankAccount;
import cis410.onlinebanking.sent.NewOnlineAccount;
import cis410.onlinebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class BankingController {
    private final AccountService accountService;

    @Autowired
    public BankingController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("login")
    @ResponseBody
    public void login(@RequestBody LoginAttempt attempt, HttpServletResponse res){
        if(accountService.verifyAccount(attempt)){
            res.setStatus(200);
        }
        else{
            res.setStatus(401);
        }
    }

    @PostMapping("/createOnlineAccount")
    @ResponseBody
    public ResponseObject createOnlineAccount(@RequestBody NewOnlineAccount onlineAccount){
        return accountService.createOnlineAccount(onlineAccount);
    }

    @PostMapping
    @ResponseBody
    public void createBankAccount(@RequestBody NewBankAccount bankAccount){
        accountService.createBankAccount(bankAccount);
    }

    

}
