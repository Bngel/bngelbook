package cn.bngel.bngelbookuserprovider8005;

import cn.bngel.bngelbookcommonapi.bean.User;
import cn.bngel.bngelbookuserprovider8001.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;

@SpringBootTest
class BngelbookUserProvider8001ApplicationTests {

    @Autowired
    private UserController userController;

    @Value("test")
    private String test;

    @Test
    @Rollback
    void saveUser() {
        userController.saveUser(new User(13L, "bngel", "bngel",
                "17605024713", "wu330998038@qq.com", null, 1,
                new Date(new java.util.Date().getTime()), new Date(new java.util.Date().getTime())));
    }

}