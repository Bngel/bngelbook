package cn.bngel.bngelbookprovider8000.dao;

import cn.bngel.bngelbookcommonapi.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    Integer saveUser(@Param("user") User user);

    Integer deleteUserById(@Param("id") Long id);

    User getUserById(@Param("id") Long id);

    User getUserByPhone(@Param("phone") String phone);

    User login(@Param("account") String account, @Param("password") String password);

    Integer updateUserById(@Param("user") User user);

    Integer getUserRegisterDays(@Param("id") Long id);

    List<User> getFriendsById(@Param("id") Long id);

    List<User> getUsersByUsername(@Param("username") String username);

    Integer judgeUserExists(@Param("phone") String account);
}
