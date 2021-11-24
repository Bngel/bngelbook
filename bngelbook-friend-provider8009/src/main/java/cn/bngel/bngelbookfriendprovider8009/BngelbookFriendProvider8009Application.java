package cn.bngel.bngelbookfriendprovider8009;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.bngel.bngelbookfriendprovider8009.dao")
public class BngelbookFriendProvider8009Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookFriendProvider8009Application.class, args);
    }

}
