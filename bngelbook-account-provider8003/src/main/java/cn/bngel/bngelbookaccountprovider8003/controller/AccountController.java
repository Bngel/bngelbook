package cn.bngel.bngelbookaccountprovider8003.controller;


import cn.bngel.bngelbookaccountprovider8003.service.AccountService;
import cn.bngel.bngelbookcommonapi.bean.Account;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "账户模块")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Account - 创建账户")
    @PostMapping("/account")
    public CommonResult saveAccount(@RequestBody Account account) {
        Integer result = accountService.saveAccount(account);
        if (result == 1) {
            log.info("创建账户: [" + account + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("创建账户: [" + account + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "Account - 删除账户")
    @DeleteMapping("/account")
    public CommonResult deleteAccountById(@RequestParam("id") Long id) {
        Integer result = accountService.deleteAccount(id);
        if (result == 1) {
            log.info("删除账户: [" + id + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("删除账户: [" + id + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "Account - 修改账户信息")
    @PutMapping("/account")
    public CommonResult updateAccountById(@RequestBody Account account) {
        if (account.getId() == null) {
            log.info("修改账户: [" + account + "] 失败");
            return new CommonResult(CommonResult.NULL_ID_ERROR_CODE, CommonResult.NULL_ID_ERROR_MESSAGE);
        }
        Integer result = accountService.updateAccountById(account);
        if (result == 1) {
            log.info("修改账户: [" + account + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("修改账户: [" + account + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "Account - 查询账户")
    @GetMapping("/account")
    public CommonResult getAccountById(@RequestParam("id") Long id) {
        Account account = accountService.getAccountById(id);
        if (account != null) {
            log.info("查询账户: [" + account + "] 成功");
            return CommonResult.commonSuccessResult(account);
        }
        else {
            log.info("查询账户: [" + id + "] 失败");
            return new CommonResult(CommonResult.FAILURE_CODE, CommonResult.UNKNOWN_MESSAGE);
        }
    }

    @ApiOperation(value = "Account - 查询用户账户")
    @GetMapping("/account/{userId}")
    public CommonResult getAccountsByUserId(@PathVariable("userId") Long userId) {
        List<Account> results = accountService.getAccountsByUserId(userId);
        if (results != null) {
            log.info("查询 ["+ userId +"] 账户成功");
            return CommonResult.commonSuccessResult(results);
        }
        else {
            log.info("查询 ["+ userId +"] 账户失败");
            return CommonResult.commonFailureResult();
        }
    }
}