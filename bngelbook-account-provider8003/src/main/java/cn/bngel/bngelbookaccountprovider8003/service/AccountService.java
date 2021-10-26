package cn.bngel.bngelbookaccountprovider8003.service;

import cn.bngel.bngelbookaccountprovider8003.bean.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountService {

    Integer saveAccount(Account account);

    Integer updateAccountById(Account account);

    Integer deleteAccount(Long id);

    Account getAccountById(Long id);

    List<Account> getAccountsByUserId(Long userId);
}
