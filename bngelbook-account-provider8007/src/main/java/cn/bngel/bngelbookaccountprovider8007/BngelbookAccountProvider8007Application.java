package cn.bngel.bngelbookaccountprovider8007;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookaccountprovider8007.dao")
public class BngelbookAccountProvider8007Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookAccountProvider8007Application.class, args);
    }

}
