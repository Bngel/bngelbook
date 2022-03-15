package cn.bngel.bngelbookuserprovider8001.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookuserprovider8001.service.PushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api(tags = "推送模块")
public class PushController {

    @Resource
    private PushService pushService;

    @PostMapping("/push/{id}")
    @ApiOperation(value = "Push - 向指定用户推送消息")
    public CommonResult pushMsgToUserById(@PathVariable("id") Long id,
                                          @RequestParam("msg") String msg) {
        pushService.pushMsgToUserById(id, msg);
        return CommonResult.commonSuccessResult();
    }

    @PostMapping("/push")
    @ApiOperation(value = "Push - 向所有用户推送消息")
    public CommonResult pushMsgToAllUsers(@RequestParam("msg") String msg) {
        pushService.pushMsgToAllUsers(msg);
        return CommonResult.commonSuccessResult();
    }
}
