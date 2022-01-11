package cn.bngel.bngelbookversionprovider8006.service;

import cn.bngel.bngelbookcommonapi.bean.Version;

public interface VersionService {

    Integer pushVersion(Version version);

    Integer deleteVersionById(Long id);

    Integer updateVersionById(Version version);

    Version getVersionById(Long id);

    Version getVersionByVersion(String version);

    Version getNewestVersion();
}
