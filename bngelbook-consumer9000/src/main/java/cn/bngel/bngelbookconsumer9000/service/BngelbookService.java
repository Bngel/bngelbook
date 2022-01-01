package cn.bngel.bngelbookconsumer9000.service;

import cn.bngel.bngelbookcommonapi.bean.*;
import cn.bngel.bngelbookconsumer9000.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@FeignClient(value = "bngelbook-provider", configuration = FeignSupportConfig.class)
public interface BngelbookService {

    /**
     * 账户模块
     */

    @PostMapping("/account")
    CommonResult<Boolean> saveAccount(@RequestBody Account account);

    @DeleteMapping("/account")
    CommonResult<Boolean> deleteAccountById(@RequestParam("id") Long id);

    @PutMapping("/account")
    CommonResult<Boolean> updateAccountById(@RequestBody Account account);

    @GetMapping("/account")
    CommonResult<Account> getAccountById(@RequestParam("id") Long id);

    @GetMapping("/account/{userId}")
    CommonResult<List<Account>> getAccountsByUserId(@PathVariable("userId") Long userId);

    /**
     * 账单模块
     */

    @PostMapping("/bill")
    CommonResult<Boolean> saveBill(@RequestBody Bill bill);

    @DeleteMapping("/bill")
    CommonResult<Boolean> deleteBillById(@RequestParam("id") Long id);

    @PutMapping("/bill")
    CommonResult<Boolean> updateBillById(@RequestBody Bill bill);

    @GetMapping("/bill")
    CommonResult<Bill> getBillById(@RequestParam("id") Long id);

    @GetMapping("/bill/book/{bookId}")
    CommonResult<List<Bill>> getBillsByBookId(@PathVariable("bookId") Long bookId);

    @GetMapping("/bill/account/{accountId}")
    CommonResult<List<Bill>> getBillsByAccountId(@PathVariable("accountId") Long accountId);

    @GetMapping("/bill/book/{bookId}/{month}")
    CommonResult<List<Bill>> getMonthBillsByBookId(@PathVariable("bookId") Long bookId,
                                                   @PathVariable("month") Integer month);

    /**
     * 账本模块
     */

    @PostMapping("/book")
    CommonResult<Boolean> saveBook(@RequestBody Book book);

    @DeleteMapping("/book")
    CommonResult<Boolean> deleteBookById(@RequestParam("id") Long id);

    @PutMapping("/book")
    CommonResult<Boolean> updateBookById(@RequestBody Book book);

    @GetMapping("/book")
    CommonResult<Book> getBookById(@RequestParam("id") Long id);

    @GetMapping("/book/{userId}")
    CommonResult<List<Book>> getBooksByUserId(@PathVariable("userId") Long userId);

    /**
     * 好友模块
     */

    @PostMapping("/friend")
    CommonResult<Boolean> addFriend(@RequestBody Friend friend);

    @DeleteMapping("/friend/id")
    CommonResult<Boolean> deleteFriendById(@RequestParam("id") Long id);

    @DeleteMapping("/friend")
    CommonResult<Boolean> deleteFriendByFriend(@RequestBody Friend friend);

    @PutMapping("/friend")
    CommonResult<Boolean> updateFriendById(@RequestBody Friend friend);

    @GetMapping("/friend")
    CommonResult<Friend> getFriendById(@RequestParam("id") Long id);

    @PostMapping("/friend/{userId}")
    CommonResult<List<Friend>> getFriendsByUserId(@PathVariable("userId") Long userId);

    @PostMapping("/friend/judge")
    CommonResult judgeFriendExists(@RequestParam("user1Id") Long user1Id, @RequestParam("user2Id") Long user2Id);

    /**
     * 用户模块
     */

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