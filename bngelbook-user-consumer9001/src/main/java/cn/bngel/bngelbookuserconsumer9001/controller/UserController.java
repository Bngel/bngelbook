package cn.bngel.bngelbookuserconsumer9001.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserconsumer9001.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @ApiOperation(value = "User - 注册用户")
    @PostMapping("/user/register")
    public CommonResult registerUser(@RequestBody User user) {
        CommonResult<Boolean> result = userService.registerUser(user);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("注册用户: [" + user + "] 成功");
        } else if (result.getCode().equals(User.USER_REGISTERED_ERROR_CODE)){
            log.info("注册用户: [" + user + "] 失败: 用户已存在");
        } else {
            log.info("注册用户: [" + user + "] 失败");
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

    @ApiOperation(value = "User - 用户累计注册时间")
    @GetMapping("/user/registerDays")
    public CommonResult<Integer> login(@RequestParam("id") Long id) {
        CommonResult<Integer> result = userService.getUserRegisterDays(id);
        if (result != null) {
            log.info("用户注册时间: [" + result.getData() + "]");
        }
        else {
            log.info("查询失败: [ id:" + id + "]");
        }
        return result;
    }

    @ApiOperation(value = "User - 获取好友列表")
    @GetMapping("/user/friends")
    public CommonResult<List<User>> getFriendsById(@RequestParam("id") Long id) {
        CommonResult<List<User>> results = userService.getFriendsById(id);
        if (results != null) {
            log.info("用户好友列表: [" + id + "] 获取成功");
        }
        else {
            log.info("用户好友列表: [" + id + "] 获取失败");
        }
        return results;
    }

    @ApiOperation(value = "User - 查询用户列表 By 用户名")
    @GetMapping("/user/{username}")
    public CommonResult<List<User>> getUsersByUsername(@PathVariable("username") String username) {
        CommonResult<List<User>> results = userService.getUsersByUsername(username);
        if (results != null) {
            log.info("用户列表: [" + username + "] 获取成功");
        }
        else {
            log.info("用户列表: [" + username + "] 获取失败");
        }
        return results;
    }

    @ApiOperation(value = "User - 发送登录短信")
    @PostMapping("/user/login/sms")
    public CommonResult loginBySms(@RequestParam("area") String area,
                                   @RequestParam("phone") String phone) {
        CommonResult checkNumber = userService.loginBySms(area, phone);
        if (checkNumber.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("用户[" + phone + "]登录: " + checkNumber.getData());
        }
        else {
            log.info("用户[" + phone + "]登录: 发送短信失败");
        }
        return checkNumber;
    }

    @ApiOperation(value = "User - 验证码验证")
    @PostMapping("/user/login/check")
    public CommonResult loginCodeCheck(@RequestParam("phone") String phone,
                                       @RequestParam("code") String code) {
        CommonResult loginCodeCheck = userService.loginCodeCheck(phone, code);
        if (loginCodeCheck.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("用户[" + phone + "]登录: " + ((User)loginCodeCheck.getData()).getUsername());
        }
        else {
            log.info("用户[" + phone + "]登录: 验证码验证失败");
        }
        return loginCodeCheck;
    }

    @ApiOperation(value = "User - 上传文件")
    @PostMapping(value = "/user/upload", headers = {"Content-Type=multipart/form-data;charset=UTF-8"})
    CommonResult uploadUserFile(@RequestParam("file") MultipartFile file,
                                   @RequestParam("bucket") String bucket,
                                   @RequestParam("cosPath") String cosPath) throws IOException {
        CommonResult result = userService.uploadUserFile(file, bucket, cosPath);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("用户上传文件: " + result.getData());
        }
        else {
            log.info("用户上传文件: 失败");
        }
        return result;
    }

    @ApiOperation(value = "User - 更新用户头像")
    @PostMapping(value = "/user/profile", headers = {"Content-Type=multipart/form-data;charset=UTF-8"})
    public CommonResult postUserProfile(@RequestParam("id") Long id, @RequestPart("profile") MultipartFile profile) throws IOException {
        CommonResult result = userService.postUserProfile(id, profile);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("用户上传头像文件: " + result.getData());
        }
        else {
            log.info("用户上传头像文件: 失败");
        }
        return result;
    }
}
