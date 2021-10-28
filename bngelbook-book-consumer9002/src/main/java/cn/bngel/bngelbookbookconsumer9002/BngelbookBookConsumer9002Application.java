package cn.bngel.bngelbookbookconsumer9002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BngelbookBookConsumer9002Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookBookConsumer9002Application.class, args);
    }

}
