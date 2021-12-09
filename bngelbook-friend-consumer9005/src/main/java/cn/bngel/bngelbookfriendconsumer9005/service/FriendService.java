package cn.bngel.bngelbookfriendconsumer9005.service;


import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.Friend;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient("bngelbook-friend-provider")
public interface FriendService {

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
}
