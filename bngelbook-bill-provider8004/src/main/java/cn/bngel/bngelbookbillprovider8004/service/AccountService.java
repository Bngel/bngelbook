package cn.bngel.bngelbookbillprovider8004.service;

import cn.bngel.bngelbookcommonapi.bean.Account;
import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient("bngelbook-account-provider")
public interface AccountService {

    @PostMapping("/account")
    CommonResult<Account> saveAccount(@RequestBody Account account);

    @DeleteMapping("/account")
    CommonResult<Account> deleteAccountById(@RequestParam("id") Long id);

    @PutMapping("/account")
    CommonResult<Account> updateAccountById(@RequestBody Account account);

    @GetMapping("/account")
    CommonResult<Account> getAccountById(@RequestParam("id") Long id);

    @GetMapping("/account/{userId}")
    CommonResult<Account> getAccountsByUserId(@PathVariable("userId") Long userId);
}
