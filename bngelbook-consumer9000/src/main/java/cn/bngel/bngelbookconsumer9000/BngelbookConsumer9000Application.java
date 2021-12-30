package cn.bngel.bngelbookconsumer9000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BngelbookConsumer9000Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookConsumer9000Application.class, args);
    }

}
