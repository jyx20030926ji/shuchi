package com.testvue.testvue.config;


import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {

        //TODO 发送者确认发送到交换机,

         RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);

         rabbitTemplate.setMandatory(true);




        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送成功: correlationData({})", correlationData);
            } else {
                log.warn("消息发送失败: correlationData({}), cause({})", correlationData, cause);
            }
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            Message message = returned.getMessage();
            log.warn("消息丢失: exchange({}), route({}), replyCode({}), replyText({}), message: {}",
                    returned.getExchange(), returned.getRoutingKey(), returned.getReplyCode(),
                    returned.getReplyText(), message.toString());
        });

        return rabbitTemplate;



    }
    @Bean
    public Queue queue1()
    {
        return new Queue("queue10");

    }
    @Bean
    public Queue queue2()
    {
        return new Queue("queue11");
    }

    @Bean
    public TopicExchange topicExchange()
    {
        return new TopicExchange("topic-jyx10");
    }

    @Bean
    public Binding binding()
    {

        return BindingBuilder.bind(queue1()).to(topicExchange()).with("topic10.*");
    }
    @Bean
    public Binding binding2()
    {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("queue11");
    }


}