package cn.bngel.bngelbookbillconsumer9004;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BngelbookBillConsumer9004Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookBillConsumer9004Application.class, args);
    }

}
