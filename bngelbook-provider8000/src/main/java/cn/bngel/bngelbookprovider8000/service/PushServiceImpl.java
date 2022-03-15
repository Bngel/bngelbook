package cn.bngel.bngelbookprovider8000.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PushServiceImpl implements PushService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void pushMsgToUserById(Long id, String msg) {
        rabbitTemplate.convertAndSend("defaultExchange", "default.user." + id, msg);
    }

    @Override
    public void pushMsgToAllUsers(String msg) {
        rabbitTemplate.convertAndSend("defaultExchange", "default.user", msg);
    }
}
