package cn.bngel.bngelbookuserprovider8001.component;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultQueueConsumer {

    @RabbitListener(queues = "defaultQueue")
    public void receive(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody());
        System.out.println(msg);
    }
}
