package cn.bngel.bngelbookuserprovider8001.service;

import cn.bngel.bngelbookuserprovider8001.bean.User;

public interface UserService{

    Integer saveUser(User user);

    Integer deleteUserById(Long id);

    Integer updateUser(User user);

    User getUserById(Long id);

    Integer updateUsernameById(Long id, String username);

    User login(String account, String password);
}