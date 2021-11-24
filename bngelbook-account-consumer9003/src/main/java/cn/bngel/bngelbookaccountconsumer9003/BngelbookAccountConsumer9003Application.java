package cn.bngel.bngelbookaccountconsumer9003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BngelbookAccountConsumer9003Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookAccountConsumer9003Application.class, args);
    }

}