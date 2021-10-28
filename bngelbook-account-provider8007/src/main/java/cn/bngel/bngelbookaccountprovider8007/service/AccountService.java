package cn.bngel.bngelbookaccountprovider8007.service;

import cn.bngel.bngelbookaccountprovider8007.bean.Account;

import java.util.List;

public interface AccountService {

    Integer saveAccount(Account account);

    Integer updateAccountById(Account account);

    Integer deleteAccount(Long id);

    Account getAccountById(Long id);

    List<Account> getAccountsByUserId(Long userId);
}
