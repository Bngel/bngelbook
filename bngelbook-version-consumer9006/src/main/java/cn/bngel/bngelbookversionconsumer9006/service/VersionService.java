package cn.bngel.bngelbookversionconsumer9006.service;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.bean.Version;
import cn.bngel.bngelbookversionconsumer9006.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(value = "bngelbook-version-provider", configuration = FeignSupportConfig.class)
public interface VersionService {

    @PostMapping("/version")
    CommonResult pushVersion(@RequestBody Version version);

    @DeleteMapping("/version")
    CommonResult deleteVersionById(@RequestParam("id") Long id);

    @PutMapping("/version")
    CommonResult updateVersionById(@RequestBody Version version);

    @GetMapping("/version/newest")
    CommonResult getNewestVersion();

    @PostMapping("/version/apk")
    CommonResult uploadApk(@RequestParam("version") String version,
                           @RequestParam("content") String content,
                           @RequestPart("apk") MultipartFile apk) throws IOException;
}
