package cn.bngel.bngelbookuserprovider8001.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserprovider8001.service.UserService;
import cn.hutool.core.lang.UUID;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "User - 创建用户")
    @PostMapping("/user")
    public CommonResult saveUser(@RequestBody User user) {
        user.setRegisterDate();
        Integer result = userService.saveUser(user);
        if (result == 1) {
            log.info("创建用户: [" + user + "] 成功");
            return CommonResult.commonSuccessResult();
        } else {
            log.info("创建用户: [" + user + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "User - 注册用户")
    @PostMapping("/user/register")
    public CommonResult registerUser(@RequestBody User user) {
        Integer result = userService.registerUser(user);
        if (result == 1) {
            log.info("注册用户: [" + user + "] 成功");
            return CommonResult.commonSuccessResult();
        } else if (result == -1){
            log.info("注册用户: [" + user + "] 失败: 用户已存在");
            return new CommonResult(User.USER_REGISTERED_ERROR_CODE, User.USER_REGISTERED_ERROR_MESSAGE);
        } else {
            log.info("注册用户: [" + user + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "User - 注销用户")
    @DeleteMapping("/user")
    public CommonResult deleteUserById(@RequestParam("id") Long id) {
        Integer result = userService.deleteUserById(id);
        if (result == 1) {
            log.info("删除用户: [" + id + "]");
            return CommonResult.commonSuccessResult();
        } else {
            log.info("删除用户: [" + id + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "User - 修改用户信息")
    @PutMapping("/user")
    public CommonResult updateUserById(@RequestBody User user) {
        if (user.getId() == null) {
            log.info("更改用户信息: [" + user + "] 失败");
            return new CommonResult(CommonResult.NULL_ID_ERROR_CODE, CommonResult.NULL_ID_ERROR_MESSAGE);
        }
        if (user.getUsername().length() > User.MAX_LENGTH_OF_USERNAME) {
            return new CommonResult(User.OVER_LENGTH_ERROR_CODE, User.OVER_LENGTH_ERROR_MESSAGE);
        }
        Integer result = userService.updateUserById(user);
        if (result == 1) {
            log.info("更改用户信息[" + user.getId() + "]: " + user.getUsername());
            return CommonResult.commonSuccessResult();
        } else {
            log.info("更改用户信息[" + user.getId() + "]: 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "User - 查询用户")
    @GetMapping("/user")
    public CommonResult getUserById(@RequestParam("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            log.info("查询用户: [" + user + "] 成功");
            return CommonResult.commonSuccessResult(user);
        }
        else {
            log.info("查询用户: [" + id + "]: 失败");
            return new CommonResult(CommonResult.FAILURE_CODE, User.UNKNOWN_USER_MESSAGE);
        }
    }

    @ApiOperation(value = "User - 用户登录")
    @PostMapping("/user/login")
    public CommonResult login(@RequestParam("account") String account,
                              @RequestParam("password") String password) {
        User user = userService.login(account, password);
        if (user != null) {
            log.info("用户登录: [" + user + "]");
            return CommonResult.commonSuccessResult(user);
        }
        else {
            log.info("登录失败: [" + account + "]");
            return new CommonResult(CommonResult.FAILURE_CODE, User.LOGIN_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "User - 用户累计注册时间")
    @GetMapping("/user/registerDays")
    public CommonResult getUserRegisterDays(@RequestParam("id") Long id) {
        Integer days = userService.getUserRegisterDays(id);
        if (days != null) {
            log.info("用户注册时间: [" + days + "]");
            return CommonResult.commonSuccessResult(days);
        }
        else {
            log.info("查询失败: [ id:" + id + "]");
            return new CommonResult(CommonResult.FAILURE_CODE, User.LOGIN_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "User - 获取好友列表")
    @GetMapping("/user/friends")
    public CommonResult getFriendsById(@RequestParam("id") Long id) {
        List<User> friends = userService.getFriendsById(id);
        if (friends != null) {
            log.info("用户好友列表: [" + id + "] 获取成功");
            return CommonResult.commonSuccessResult(friends);
        }
        else {
            log.info("用户好友列表: [" + id + "] 获取失败");
            return new CommonResult(CommonResult.FAILURE_CODE, User.LOGIN_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "User - 查询用户列表 by 用户名")
    @GetMapping("/user/{username}")
    public CommonResult getUsersByUsername(@PathVariable("username") String username) {
        List<User> users = userService.getUsersByUsername(username);
        if (users != null) {
            log.info("用户列表: [" + username + "] 获取成功");
            return CommonResult.commonSuccessResult(users);
        }
        else {
            log.info("用户列表: [" + username + "] 获取失败");
            return new CommonResult(CommonResult.FAILURE_CODE, User.LOGIN_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "User - 发送登录短信")
    @PostMapping("/user/login/sms")
    public CommonResult loginBySms(@RequestParam("area") String area,
                                   @RequestParam("phone") String phone) {
        String checkNumber = userService.smsLogin(area, phone);
        if (checkNumber.equals("")) {
            log.info("用户[" + phone + "]登录: 发送短信失败");
            return CommonResult.commonFailureResult();
        }
        else {
            log.info("用户[" + phone + "]登录: " + checkNumber);
            return CommonResult.commonSuccessResult("OK");
        }
    }

    @ApiOperation(value = "User - 验证码验证")
    @PostMapping("/user/login/check")
    public CommonResult loginCodeCheck(@RequestParam("phone") String phone,
                                       @RequestParam("code") String code) {
        if (userService.smsCheck(phone, code)) {
            log.info("用户[" + phone + "]登录: 验证码验证成功");
            if (userService.judgeUserExists(phone) == 0) {
                User user = new User();
                user.setUsername(UUID.fastUUID().toString(true).substring(0, 10));
                user.setPhone(phone);
                user.setPassword(UUID.fastUUID().toString(true));
                user.setRegisterDate();
                Integer result = userService.saveUser(user);
                if (result != null) {
                    log.info("用户[" + phone + "]登录: [ID:"+ result + "]新用户注册成功");
                    return CommonResult.commonSuccessResult();
                }
                else {
                    log.info("用户[" + phone + "]登录: 新用户注册失败");
                    return CommonResult.commonFailureResult();
                }
            }
            else {
                User user = userService.getUserByPhone(phone);
                if (user != null) {
                    log.info("用户[" + phone + "]登录: 用户登录成功");
                    return CommonResult.commonSuccessResult(user);
                }
                else {
                    log.info("用户[" + phone + "]登录: 用户登录失败");
                    return CommonResult.commonFailureResult();
                }
            }
        }
        else {
            log.info("用户[" + phone + "]登录: 验证码验证失败");
            return CommonResult.commonFailureResult();
        }
    }
}
