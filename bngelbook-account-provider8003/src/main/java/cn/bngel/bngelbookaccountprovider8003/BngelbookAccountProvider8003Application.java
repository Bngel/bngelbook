package cn.bngel.bngelbookaccountprovider8003;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookaccountprovider8003.dao")
public class BngelbookAccountProvider8003Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookAccountProvider8003Application.class, args);
    }

}
