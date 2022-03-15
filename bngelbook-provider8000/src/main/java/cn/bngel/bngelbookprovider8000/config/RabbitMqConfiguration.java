package cn.bngel.bngelbookprovider8000.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfiguration {

    // 默认交换机名称
    public static final String DEFAULT_EXCHANGE = "defaultExchange";

    // 默认队列名称
    public static final String DEFAULT_QUEUE = "defaultQueue";

    // 默认死信交换机
    public static final String DEFAULT_DEAD_EXCHANGE = "deadDefaultExchange";

    // 默认死信队列
    public static final String DEFAULT_DEAD_QUEUE = "deadDefaultQueue";

    // 默认死信队列
    public static final String DEFAULT_DEAD_ROUTING_KEY = "default.dead";

    // 默认routingKey
    public static final String DEFAULT_ROUTING_KEY = "default.user";

    @Bean("defaultExchange")
    public TopicExchange defaultExchange() {
        return new TopicExchange(DEFAULT_EXCHANGE);
    }

    @Bean("defaultDeadExchange")
    public TopicExchange defaultDeadExchange() {
        return new TopicExchange(DEFAULT_DEAD_EXCHANGE);
    }

    @Bean("defaultQueue")
    public Queue defaultQueue() {
        return QueueBuilder.durable(DEFAULT_QUEUE)
                .deadLetterExchange(DEFAULT_DEAD_EXCHANGE)
                .deadLetterRoutingKey(DEFAULT_DEAD_ROUTING_KEY)
                .ttl(10000)
                .build();
    }

    @Bean("defaultDeadQueue")
    public Queue defaultDeadQueue() {
        return QueueBuilder.durable(DEFAULT_DEAD_QUEUE)
                .build();
    }

    // 绑定
    @Bean
    public Binding defaultQueueBindingDefaultExchange(@Qualifier("defaultQueue") Queue defaultQueue,
                                                      @Qualifier("defaultExchange") TopicExchange defaultExchange) {
        return BindingBuilder.bind(defaultQueue).to(defaultExchange).with(DEFAULT_ROUTING_KEY);
    }

    // 绑定
    @Bean
    public Binding defaultDeadQueueBindingDefaultDeadExchange(@Qualifier("defaultDeadQueue") Queue defaultDeadQueue,
                                                      @Qualifier("defaultExchange") TopicExchange defaultDeadExchange) {
        return BindingBuilder.bind(defaultDeadQueue).to(defaultDeadExchange).with(DEFAULT_DEAD_ROUTING_KEY);
    }

}
