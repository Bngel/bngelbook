package cn.bngel.bngelbookversionconsumer9006;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class BngelbookVersionConsumer9006Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookVersionConsumer9006Application.class, args);
    }

}
