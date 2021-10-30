package cn.bngel.bngelbookbillprovider8008;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookbillprovider8008.dao")
public class BngelbookBillProvider8008Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookBillProvider8008Application.class, args);
    }

}
