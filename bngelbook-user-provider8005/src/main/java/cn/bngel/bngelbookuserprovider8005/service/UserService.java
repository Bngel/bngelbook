package cn.bngel.bngelbookuserprovider8005.service;

import cn.bngel.bngelbookcommonapi.bean.User;

import java.util.List;

public interface UserService{

    Integer saveUser(User user);

    Integer deleteUserById(Long id);

    Integer updateUserById(User user);

    User getUserById(Long id);

    Integer updateUsernameById(Long id, String username);

    User login(String account, String password);

    Integer getUserRegisterDays(Long id);

    List<User> getFriendsById(Long id);

    List<User> getUsersByUsername(String username);
}
