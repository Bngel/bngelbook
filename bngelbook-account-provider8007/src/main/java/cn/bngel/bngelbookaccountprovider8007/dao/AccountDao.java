package cn.bngel.bngelbookaccountprovider8007.dao;

import cn.bngel.bngelbookaccountprovider8007.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountDao {

    Integer saveAccount(@Param("account") Account account);

    Integer updateAccountById(@Param("account") Account account);

    Integer deleteAccount(@Param("id") Long id);

    Account getAccountById(@Param("id") Long id);

    List<Account> getAccountsByUserId(@Param("userId") Long userId);
}
