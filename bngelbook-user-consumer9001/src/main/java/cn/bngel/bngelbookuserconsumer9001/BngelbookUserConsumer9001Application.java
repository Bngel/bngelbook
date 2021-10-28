package cn.bngel.bngelbookuserconsumer9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BngelbookUserConsumer9001Application {

    public static void main(String[] args) {
        SpringApplication.run(BngelbookUserConsumer9001Application.class, args);
    }

}
