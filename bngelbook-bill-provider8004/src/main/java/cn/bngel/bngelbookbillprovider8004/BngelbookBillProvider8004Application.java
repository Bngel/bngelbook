package cn.bngel.bngelbookbillprovider8004;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookbillprovider8004.dao")
public class BngelbookBillProvider8004Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookBillProvider8004Application.class, args);
    }

}
