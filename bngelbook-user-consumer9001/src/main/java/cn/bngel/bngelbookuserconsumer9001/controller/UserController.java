package cn.bngel.bngelbookuserconsumer9001.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserconsumer9001.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "用户模块")
@RequestMapping("/consumer")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "User - 创建用户")
    @PostMapping("/user")
    public CommonResult<Boolean> saveUser(@RequestBody User user) {
        CommonResult<Boolean> result = userService.saveUser(user);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("创建用户: [" + user + "] 成功");
        } else {
            log.info("创建用户: [" + user + "] 失败");
        }
        return result;
    }

    @ApiOperation(value = "User - 注销用户")
    @DeleteMapping("/user")
    public CommonResult<Boolean> deleteUserById(@RequestParam("id") Long id) {
        CommonResult<Boolean> result = userService.deleteUserById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("删除用户: [" + id + "]");
        } else {
            log.info("删除用户: [" + id + "] 失败");
        }
        return result;
    }

    @ApiOperation(value = "User - 修改用户信息")
    @PutMapping("/user")
    public CommonResult<Boolean> updateUserById(@RequestBody User user) {
        CommonResult<Boolean> result = userService.updateUserById(user);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("更改用户信息[" + user.getId() + "]: " + user.getUsername());
        } else {
            log.info("更改用户信息[" + user.getId() + "]: 失败");
        }
        return result;
    }

    @ApiOperation(value = "User - 查询用户")
    @GetMapping("/user")
    public CommonResult<User> getUserById(@RequestParam("id") Long id) {
        CommonResult<User> result = userService.getUserById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询用户: [" + id + "] 成功");
        }
        else {
            log.info("查询用户: [" + id + "]: 失败");
        }
        return result;
    }

    @ApiOperation(value = "User - 用户登录")
    @PostMapping("/user/login")
    public CommonResult<User> login(@RequestParam("account") String account,
                              @RequestParam("password") String password) {
        CommonResult<User> result = userService.login(account, password);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("登录成功: [" + account + "]");
        }
        else {
            log.info("登录失败: [" + account + "]");
        }
        return result;
    }
}
