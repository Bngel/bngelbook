package cn.bngel.bngelbookbookprovider8002;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookbookprovider8002.dao")
public class BngelbookBookProvider8002Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookBookProvider8002Application.class, args);
    }

}
