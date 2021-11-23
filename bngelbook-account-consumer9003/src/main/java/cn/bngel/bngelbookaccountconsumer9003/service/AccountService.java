package cn.bngel.bngelbookaccountconsumer9003.service;

import cn.bngel.bngelbookcommonapi.bean.Account;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient("bngelbook-account-provider")
public interface AccountService {

    @PostMapping("/account")
    CommonResult<Boolean> saveAccount(@RequestBody Account account);

    @DeleteMapping("/account")
    CommonResult<Boolean> deleteAccountById(@RequestParam("id") Long id);

    @PutMapping("/account")
    CommonResult<Boolean> updateAccountById(@RequestBody Account account);

    @GetMapping("/account")
    CommonResult<Account> getAccountById(@RequestParam("id") Long id);

    @GetMapping("/account/{userId}")
    CommonResult<List<Account>> getAccountsByUserId(@PathVariable("userId") Long userId);
}
