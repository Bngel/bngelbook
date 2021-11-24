package cn.bngel.bngelbookfriendprovider8009.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.Friend;
import cn.bngel.bngelbookfriendprovider8009.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "好友模块")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/friend")
    @ApiOperation(value = "Friend - 添加好友")
    public CommonResult addFriend(@RequestBody Friend friend) {
        try {
            Integer result = friendService.addFriend(friend);
            if (result == 1) {
                log.info("添加好友: [" + friend + "] 成功");
                return CommonResult.commonSuccessResult();
            }
            else if (result == -1) {
                log.info("添加好友: [" + friend + "] 失败: 好友已存在");
                return new CommonResult(Friend.FRIEND_EXISTS_ERROR_CODE, Friend.FRIEND_EXISTS_ERROR_MESSAGE);
            }
            else if (result == -2) {
                log.info("添加好友: [" + friend + "] 失败: 不能添加自己为好友");
                return new CommonResult(Friend.USER_ADD_SELF_ERROR_CODE, Friend.USER_ADD_SELF_ERROR_MESSAGE);
            }
            else {
                log.info("添加好友: [" + friend + "] 失败");
                return CommonResult.commonFailureResult();
            }
        } catch (DataIntegrityViolationException e) {
            return new CommonResult(Friend.USER_NOT_EXISTS_ERROR_CODE, Friend.USER_NOT_EXISTS_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.commonFailureResult();
        }
    }

    @DeleteMapping("/friend/id")
    @ApiOperation(value = "Friend - 删除好友 by 关系ID")
    public CommonResult deleteFriendById(@RequestParam("id") Long id) {
        Integer result = friendService.deleteFriendById(id);
        if (result == 1) {
            log.info("删除好友关系: [" + id + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("删除好友关系: [" + id + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @DeleteMapping("/friend")
    @ApiOperation(value = "Friend - 删除好友 by 用户ID")
    public CommonResult deleteFriendByUserId(@RequestParam("friend") Friend friend) {
        Integer result = friendService.deleteFriendByUserId(friend);
        if (result == 1) {
            log.info("删除好友关系: [" + friend + "] 成功");
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("删除好友关系: [" + friend + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @PutMapping("/friend")
    @ApiOperation(value = "Friend - 修改好友关系 by 关系ID")
    public CommonResult updateFriendById(@RequestBody Friend friend) {
        try {
            Integer result = friendService.updateFriendById(friend);
            if (result == 1) {
                log.info("修改好友关系: [" + friend + "] 成功");
                return CommonResult.commonSuccessResult();
            }
            else {
                log.info("修改好友关系: [" + friend + "] 失败");
                return CommonResult.commonFailureResult();
            }
        } catch (DataIntegrityViolationException e) {
            return new CommonResult(Friend.USER_NOT_EXISTS_ERROR_CODE, Friend.USER_NOT_EXISTS_ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/friend")
    @ApiOperation(value = "Friend - 查询好友关系 by 关系ID")
    public CommonResult getFriendById(@RequestParam("id") Long id) {
        Friend result = friendService.getFriendById(id);
        if (result != null) {
            log.info("查询好友关系: [" + result + "] 成功");
            return new CommonResult(CommonResult.SUCCESS_CODE, result, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            log.info("查询好友关系: [" + result + "] 失败");
            return CommonResult.commonFailureResult();
        }
    }

    @GetMapping("/friend/{userId}")
    @ApiOperation(value = "Friend - 查询好友关系 by 用户ID")
    public CommonResult getFriendsByUserId(@PathVariable("userId") Long userId) {
        List<Friend> result = friendService.getFriendsByUserId(userId);
        if (result != null) {
            log.info("查询 ["+ userId +"] 好友: [" + result + "] 成功");
            return new CommonResult(CommonResult.SUCCESS_CODE, result, CommonResult.SUCCESS_MESSAGE);
        }
        else {
            log.info("查询 ["+ userId +"] 好友失败");
            return CommonResult.commonFailureResult();
        }
    }
}
