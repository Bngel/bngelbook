package cn.bngel.bngelbookuserprovider8001.service;

import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserprovider8001.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public Integer saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public Integer updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User login(String account, String password) {
        return userDao.login(account, password);
    }

    @Override
    public Integer getUserRegisterDays(Long id) {
        return userDao.getUserRegisterDays(id);
    }

    @Override
    public List<User> getFriendsById(Long id) {
        return userDao.getFriendsById(id);
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        return userDao.getUsersByUsername(username);
    }

    @Override
    public Integer updateUsernameById(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return userDao.updateUserById(user);
    }


}
