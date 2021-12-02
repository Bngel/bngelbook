package cn.bngel.bngelbookuserprovider8005.dao;

import cn.bngel.bngelbookcommonapi.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    Integer saveUser(@Param("user") User user);

    Integer deleteUserById(@Param("id") Long id);

    User getUserById(@Param("id") Long id);

    User login(@Param("account") String account, @Param("password") String password);

    Integer updateUserById(@Param("user") User user);

    Integer getUserRegisterDays(@Param("id") Long id);

    List<User> getFriendsById(@Param("id") Long id);

    List<User> getUsersByUsername(@Param("username") String username);
}
