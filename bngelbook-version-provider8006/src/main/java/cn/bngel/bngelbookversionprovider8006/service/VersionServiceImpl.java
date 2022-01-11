package cn.bngel.bngelbookversionprovider8006.service;

import cn.bngel.bngelbookcommonapi.bean.Version;
import cn.bngel.bngelbookversionprovider8006.dao.VersionDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VersionServiceImpl implements VersionService{

    @Resource
    private VersionDao versionDao;

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
}
