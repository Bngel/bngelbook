package cn.bngel.bngelbookfriendconsumer9005.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.Friend;
import cn.bngel.bngelbookfriendconsumer9005.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "好友模块")
@RequestMapping("/consumer")
public class FriendController {

    @Autowired
    private FriendService friendService;


    @PostMapping("/friend")
    @ApiOperation(value = "Friend - 添加好友")
    public CommonResult addFriend(@RequestBody Friend friend) {
        CommonResult<Boolean> result = friendService.addFriend(friend);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("添加好友: [" + friend + "] 成功");
        }
        else if (result.getCode().equals(Friend.FRIEND_EXISTS_ERROR_CODE)) {
            log.info("添加好友: [" + friend + "] 失败: 好友已存在");
        }
        else if (result.getCode().equals(Friend.USER_ADD_SELF_ERROR_CODE)) {
            log.info("添加好友: [" + friend + "] 失败: 不能添加自己为好友");
        }
        else {
            log.info("添加好友: [" + friend + "] 失败");
        }
        return result;
    }

    @DeleteMapping("/friend/id")
    @ApiOperation(value = "Friend - 删除好友 by 关系ID")
    public CommonResult deleteFriendById(@RequestParam("id") Long id) {
        CommonResult<Boolean> result = friendService.deleteFriendById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("删除好友关系: [" + id + "] 成功");
        }
        else {
            log.info("删除好友关系: [" + id + "] 失败");
        }
        return result;
    }

    @DeleteMapping("/friend")
    @ApiOperation(value = "Friend - 删除好友 by 用户ID")
    public CommonResult deleteFriendByUserId(@RequestBody Friend friend) {
        CommonResult<Boolean> result = friendService.deleteFriendByFriend(friend);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("删除好友关系: [" + friend + "] 成功");
        }
        else {
            log.info("删除好友关系: [" + friend + "] 失败");
        }
        return result;
    }

    @PutMapping("/friend")
    @ApiOperation(value = "Friend - 修改好友关系 by 关系ID")
    public CommonResult updateFriendById(@RequestBody Friend friend) {
        CommonResult<Boolean> result = friendService.updateFriendById(friend);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("修改好友关系: [" + friend + "] 成功");
        }
        else if (result.getCode().equals(Friend.USER_NOT_EXISTS_ERROR_CODE)) {
            log.info("修改好友关系: [" + friend + "] 失败: 用户不存在");
        }
        else {
            log.info("修改好友关系: [" + friend + "] 失败");
        }
        return result;
    }

    @GetMapping("/friend")
    @ApiOperation(value = "Friend - 查询好友关系 by 关系ID")
    public CommonResult getFriendById(@RequestParam("id") Long id) {
        CommonResult<Friend> result = friendService.getFriendById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询好友关系: [" + result + "] 成功");
        }
        else {
            log.info("查询好友关系: [" + result + "] 失败");
        }
        return result;
    }

    @GetMapping("/friend/{userId}")
    @ApiOperation(value = "Friend - 查询好友关系 by 用户ID")
    public CommonResult getFriendsByUserId(@PathVariable("userId") Long userId) {
        CommonResult<List<Friend>> result = friendService.getFriendsByUserId(userId);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("查询 ["+ userId +"] 好友: [" + result + "] 成功");
        }
        else {
            log.info("查询 ["+ userId +"] 好友失败");
        }
        return result;
    }
}
