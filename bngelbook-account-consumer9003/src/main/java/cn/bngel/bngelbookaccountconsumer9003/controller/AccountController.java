package cn.bngel.bngelbookaccountconsumer9003.controller;

import cn.bngel.bngelbookaccountconsumer9003.service.AccountService;
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
@RequestMapping("/consumer")
@Api(tags = "账户模块")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Account - 创建账户")
    @PostMapping("/account")
    public CommonResult saveAccount(@RequestBody Account account) {
        CommonResult result = accountService.saveAccount(account);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("创建账户: [" + account + "] 成功");
        }
        else {
            log.info("创建账户: [" + account + "] 失败");
        }
        return result;
    }

    @ApiOperation(value = "Account - 删除账户")
    @DeleteMapping("/account")
    public CommonResult deleteAccountById(@RequestParam("id") Long id) {
        CommonResult result = accountService.deleteAccountById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("删除账户: [" + id + "] 成功");
        }
        else {
            log.info("删除账户: [" + id + "] 失败");
        }
        return result;
    }

    @ApiOperation(value = "Account - 修改账户信息")
    @PutMapping("/account")
    public CommonResult updateAccountById(@RequestBody Account account) {
        CommonResult result = accountService.updateAccountById(account);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("更改账户: [" + account + "] 成功");
        }
        else {
            log.info("更改账户: [" + account + "] 失败");
        }
        return result;
    }

    @ApiOperation(value = "Account - 查询账户")
    @GetMapping("/account")
    public CommonResult getAccountById(@RequestParam("id") Long id) {
        CommonResult result = accountService.getAccountById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询账户: [" + id + "] 成功");
        }
        else {
            log.info("查询账户: [" + id + "] 失败");
        }
        return result;
    }

    @ApiOperation(value = "Account - 查询用户账户")
    @GetMapping("/account/{userId}")
    public CommonResult getAccountsByUserId(@PathVariable("userId") Long userId) {
        CommonResult result = accountService.getAccountsByUserId(userId);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询用户账户: [" + userId + "] 成功");
        }
        else {
            log.info("查询用户账户: [" + userId + "] 失败");
        }
        return result;
    }
}
