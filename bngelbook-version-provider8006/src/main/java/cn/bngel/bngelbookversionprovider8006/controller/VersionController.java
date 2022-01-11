package cn.bngel.bngelbookversionprovider8006.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.Version;
import cn.bngel.bngelbookversionprovider8006.service.VersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "版本模块")
@Slf4j
public class VersionController {

    @Resource
    private VersionService versionService;

    @ApiOperation(value = "Version - 推送新版本")
    @PostMapping("/version")
    public CommonResult pushVersion(@RequestBody Version version) {
        Integer pushVersion = versionService.pushVersion(version);
        if (pushVersion == 1) {
            log.info("推送新版本: " + version.getVersion());
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("推送新版本失败: " + version.toString());
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "Version - 删除版本信息")
    @DeleteMapping("/version")
    public CommonResult deleteVersionById(@RequestParam("id") Long id) {
        Integer pushVersion = versionService.deleteVersionById(id);
        if (pushVersion == 1) {
            log.info("删除版本: " + id);
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("删除版本失败: " + id);
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "Version - 修改版本信息")
    @PutMapping("/version")
    public CommonResult updateVersionById(@RequestBody Version version) {
        Integer pushVersion = versionService.updateVersionById(version);
        if (pushVersion == 1) {
            log.info("修改版本信息: " + version.getVersion());
            return CommonResult.commonSuccessResult();
        }
        else {
            log.info("修改版本信息失败: " + version.toString());
            return CommonResult.commonFailureResult();
        }
    }

    @ApiOperation(value = "Version - 获取最新版本")
    @GetMapping("/version/newest")
    public CommonResult getNewestVersion() {
        Version version = versionService.getNewestVersion();
        if (version != null) {
            log.info("最新版本: " + version.getVersion());
            return CommonResult.commonSuccessResult(version);
        }
        else {
            log.info("最新版本获取失败: " + version);
            return CommonResult.commonFailureResult();
        }
    }
}
