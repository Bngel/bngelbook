package cn.bngel.bngelbookaccountprovider8007.service;

import cn.bngel.bngelbookcommonapi.bean.Account;
import cn.bngel.bngelbookaccountprovider8007.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Override
    public Integer saveAccount(Account account) {
        return accountDao.saveAccount(account);
    }

    @Override
    public Integer updateAccountById(Account account) {
        return accountDao.updateAccountById(account);
    }

    @Override
    public Integer deleteAccount(Long id) {
        return accountDao.deleteAccount(id);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDao.getAccountById(id);
    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        return accountDao.getAccountsByUserId(userId);
    }
}
