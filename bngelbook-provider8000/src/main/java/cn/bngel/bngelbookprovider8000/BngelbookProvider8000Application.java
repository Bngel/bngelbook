package cn.bngel.bngelbookprovider8000;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.bngel.bngelbookprovider8000.dao")
public class BngelbookProvider8000Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookProvider8000Application.class, args);
    }

}
