package cn.bngel.bngelbookversionprovider8006.service;

import cn.bngel.bngelbookcommonapi.bean.Version;
import cn.bngel.bngelbookcommonapi.utils.TencentCosUtils;
import cn.bngel.bngelbookversionprovider8006.dao.VersionDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class VersionServiceImpl implements VersionService{

    @Resource
    private VersionDao versionDao;

    @Value("${tencent-cloud.SecretId}")
    private String secretId;

    @Value("${tencent-cloud.SecretKey}")
    private String secretKey;

    @Value("${tencent-cloud.region}")
    private String region;

    @Value("${tencent-cloud.APPID}")
    private String appId;

    @Override
    public Integer pushVersion(Version version) {
        return versionDao.pushVersion(version);
    }

    @Override
    public Integer deleteVersionById(Long id) {
        return versionDao.deleteVersionById(id);
    }

    @Override
    public Integer updateVersionById(Version version) {
        return versionDao.updateVersionById(version);
    }

    @Override
    public Version getVersionById(Long id) {
        return versionDao.getVersionById(id);
    }

    @Override
    public Version getVersionByVersion(String version) {
        return versionDao.getVersionByVersion(version);
    }

    @Override
    public Version getNewestVersion() {
        return versionDao.getNewestVersion();
    }

    @Override
    public Version uploadNewVersion(MultipartFile file, String ver, String content) throws IOException {
        TencentCosUtils tencentCosUtils = new TencentCosUtils(secretId, secretKey, region);
        Version version = new Version(null, ver, content);
        String bucketName = "bngelbook-version-" + appId;
        String result = tencentCosUtils.uploadFile(file, bucketName, "bngelbook-" + ver + ".apk");
        if (result != null) {
            Integer pushResult = versionDao.pushVersion(version);
            if (pushResult == 1)
                return version;
            else
                return null;
        }
        else
            return null;
    }
}
