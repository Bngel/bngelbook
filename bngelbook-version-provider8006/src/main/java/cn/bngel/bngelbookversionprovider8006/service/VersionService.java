package cn.bngel.bngelbookversionprovider8006.service;

import cn.bngel.bngelbookcommonapi.bean.Version;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VersionService {

    Integer pushVersion(Version version);

    Integer deleteVersionById(Long id);

    Integer updateVersionById(Version version);

    Version getVersionById(Long id);

    Version getVersionByVersion(String version);

    Version getNewestVersion();

    Version uploadNewVersion(MultipartFile file, String ver, String content) throws IOException;
}
