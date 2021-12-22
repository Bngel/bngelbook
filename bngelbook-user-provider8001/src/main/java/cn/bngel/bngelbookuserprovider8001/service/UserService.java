package cn.bngel.bngelbookuserprovider8001.service;

import cn.bngel.bngelbookcommonapi.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService{

    Integer saveUser(User user);

    Integer deleteUserById(Long id);

    Integer updateUserById(User user);

    User getUserById(Long id);

    User getUserByPhone(String phone);

    Integer updateUsernameById(Long id, String username);

    User login(String account, String password);

    Integer getUserRegisterDays(Long id);

    List<User> getFriendsById(Long id);

    List<User> getUsersByUsername(String username);

    Integer registerUser(User user);

    String smsLogin(String area, String phone);

    Boolean smsCheck(String phone, String code);

    Integer judgeUserExists(String phone);
}
