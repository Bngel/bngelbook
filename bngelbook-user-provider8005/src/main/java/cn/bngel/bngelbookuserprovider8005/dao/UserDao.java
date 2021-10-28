package cn.bngel.bngelbookuserprovider8005.dao;

import cn.bngel.bngelbookcommonapi.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    Integer saveUser(@Param("user") User user);

    Integer deleteUserById(@Param("id") Long id);

    User getUserById(@Param("id") Long id);

    User login(@Param("account") String account, @Param("password") String password);

    Integer updateUserById(@Param("user") User user);
}
