package cn.bngel.bngelbookversionprovider8006.dao;

import cn.bngel.bngelbookcommonapi.bean.Version;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VersionDao {

    Integer pushVersion(@Param("version") Version version);

    Integer deleteVersionById(@Param("id") Long id);

    Integer updateVersionById(@Param("version") Version version);

    Version getVersionById(@Param("id") Long id);

    Version getVersionByVersion(@Param("version") String version);

    Version getNewestVersion();

}
