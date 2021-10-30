package cn.bngel.bngelbookuserconsumer9001.service;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@FeignClient("bngelbook-user-provider")
@Service
public interface UserService {

    @PostMapping("/user")
    CommonResult<User> saveUser(@RequestBody User user);

    @DeleteMapping("/user")
    CommonResult<User> deleteUserById(@RequestParam("id") Long id);

    @PutMapping("/user")
    CommonResult<User> updateUserById(@RequestBody User user);

    @GetMapping("/user")
    CommonResult<User> getUserById(@RequestParam("id") Long id);

    @PostMapping("/user/login")
    CommonResult<User> login(@RequestParam("account") String account,
                       @RequestParam("password") String password);
}
