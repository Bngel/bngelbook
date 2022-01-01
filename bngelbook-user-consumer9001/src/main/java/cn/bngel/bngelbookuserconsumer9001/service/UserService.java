package cn.bngel.bngelbookuserconsumer9001.service;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserconsumer9001.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@FeignClient(value = "bngelbook-user-provider", configuration = FeignSupportConfig.class)
@Service
public interface UserService {

    @PostMapping("/user")
    CommonResult<Boolean> saveUser(@RequestBody User user);

    @DeleteMapping("/user")
    CommonResult<Boolean> deleteUserById(@RequestParam("id") Long id);

    @PutMapping("/user")
    CommonResult<Boolean> updateUserById(@RequestBody User user);

    @GetMapping("/user")
    CommonResult<User> getUserById(@RequestParam("id") Long id);

    @PostMapping("/user/login")
    CommonResult<User> login(@RequestParam("account") String account,
                       @RequestParam("password") String password);

    @GetMapping("/user/registerDays")
    CommonResult<Integer> getUserRegisterDays(@RequestParam("id") Long id);

    @GetMapping("/user/friends")
    CommonResult<List<User>> getFriendsById(@RequestParam("id") Long id);

    @GetMapping("/user/{username}")
    CommonResult<List<User>> getUsersByUsername(@PathVariable("username") String username);

    @PostMapping("/user/register")
    CommonResult<Boolean> registerUser(@RequestBody User user);

    @PostMapping("/user/login/sms")
    CommonResult<String> loginBySms(@RequestParam("area") String area, @RequestParam("phone") String phone);

    @PostMapping("/user/login/check")
    CommonResult<User> loginCodeCheck(@RequestParam("phone") String phone,
                                       @RequestParam("code") String code);

    @PostMapping(value = "/user/profile/upload", headers = {"Content-Type=multipart/form-data;charset=UTF-8"})
    CommonResult<String> uploadUserProfile(@RequestPart("profile") MultipartFile profile) throws IOException;

    @PostMapping(value = "/user/profile", headers = {"Content-Type=multipart/form-data;charset=UTF-8"})
    CommonResult<String> postUserProfile(@RequestParam("id") Long id, @RequestPart("profile") MultipartFile profile) throws IOException;
}
