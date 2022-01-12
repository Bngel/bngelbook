package cn.bngel.bngelbookconsumer9000.controller;


import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.Version;
import cn.bngel.bngelbookconsumer9000.service.BngelbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "版本模块")
@Slf4j
@RequestMapping("/consumer")
public class VersionController {

    @Resource
    private BngelbookService versionService;

    @ApiOperation(value = "Version - 推送新版本")
    @PostMapping("/version")
    public CommonResult pushVersion(@RequestBody Version version) {
        CommonResult result = versionService.pushVersion(version);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("推送新版本: " + version.getVersion());
        }
        else {
            log.info("推送新版本失败: " + version.toString());
        }
        return result;
    }

    @ApiOperation(value = "Version - 删除版本信息")
    @DeleteMapping("/version")
    public CommonResult deleteVersionById(@RequestParam("id") Long id) {
        CommonResult result = versionService.deleteVersionById(id);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("删除版本: " + id);
        }
        else {
            log.info("删除版本失败: " + id);
        }
        return result;
    }

    @ApiOperation(value = "Version - 修改版本信息")
    @PutMapping("/version")
    public CommonResult updateVersionById(@RequestBody Version version) {
        CommonResult result = versionService.updateVersionById(version);
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("修改版本信息: " + version.getVersion());
        }
        else {
            log.info("修改版本信息失败: " + version.toString());
        }
        return result;
    }

    @ApiOperation(value = "Version - 获取最新版本")
    @GetMapping("/version/newest")
    public CommonResult getNewestVersion() {
        CommonResult result = versionService.getNewestVersion();
        if (result.getCode().equals(CommonResult.SUCCESS_CODE)) {
            log.info("最新版本: " + result);
        }
        else {
            log.info("最新版本获取失败: " + result);
        }
        return result;
    }

}
