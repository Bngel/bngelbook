package cn.bngel.bngelbookfriendconsumer9005;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BngelbookFriendConsumer9005Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookFriendConsumer9005Application.class, args);
    }

}
