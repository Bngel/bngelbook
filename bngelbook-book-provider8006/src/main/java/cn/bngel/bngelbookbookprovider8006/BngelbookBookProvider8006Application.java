package cn.bngel.bngelbookbookprovider8006;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookbookprovider8006.dao")
public class BngelbookBookProvider8006Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookBookProvider8006Application.class, args);
    }

}
