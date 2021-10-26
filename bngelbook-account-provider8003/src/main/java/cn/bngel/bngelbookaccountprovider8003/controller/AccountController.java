package cn.bngel.bngelbookaccountprovider8003.controller;

import cn.bngel.bngelbookaccountprovider8003.bean.Account;
import cn.bngel.bngelbookaccountprovider8003.service.AccountService;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public CommonResult saveAccount(@RequestBody Account account) {
        Integer result = accountService.saveAccount(account);
        if (result == 1) {
            return CommonResult.commonSuccessResult();
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }

    @DeleteMapping("/account")
    public CommonResult deleteAccountById(@RequestParam("id") Long id) {
        Integer result = accountService.deleteAccount(id);
        if (result >= 1) {
            return CommonResult.commonSuccessResult();
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }

    @PutMapping("/account")
    public CommonResult updateAccountById(@RequestBody Account account) {
        Integer result = accountService.updateAccountById(account);
        if (result >= 1) {
            return CommonResult.commonSuccessResult();
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/account")
    public CommonResult getAccountById(@RequestParam("id") Long id) {
        Account account = accountService.getAccountById(id);
        if (account != null) {
            return new CommonResult(CommonResult.SUCCESS_CODE, account, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            return new CommonResult(CommonResult.FAILURE_CODE, Account.UNKNOWN_MESSAGE);
        }
    }

    @GetMapping("/account/{userId}")
    public CommonResult getAccountsByUserId(@PathVariable("userId") Long userId) {
        List<Account> results = accountService.getAccountsByUserId(userId);
        if (results != null) {
            return new CommonResult(CommonResult.SUCCESS_CODE, results, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            return CommonResult.commonFailureResult();
        }
    }
}