package cn.bngel.bngelbookuserprovider8001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookuserprovider8001.dao")
public class BngelbookUserProvider8001Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookUserProvider8001Application.class, args);
    }

}
