package cn.bngel.bngelbookuserprovider8001.service;

import cn.bngel.bngelbookuserprovider8001.bean.User;
import cn.bngel.bngelbookuserprovider8001.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User login(String account, String password) {
        return userMapper.login(account, password);
    }

    @Override
    public Integer updateUsernameById(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return userMapper.updateById(user);
    }
}
