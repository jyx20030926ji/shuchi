package com.testvue.testvue.consumer;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class MessageConsumer {


    @RabbitListener(queues = "queue10")
    public void messageConsumer(String message)
    {

        log.info("queue10监听到的消息为：{}",message);
    }


    @RabbitListener(queues="queue11")
    public void messageConsumer3(String textmessage, Message message, Channel channel) throws IOException {
           final long deliverTag=message.getMessageProperties().getDeliveryTag();

           try{

               log.info("message:----{}",textmessage);

               channel.basicAck(deliverTag,false);
           }
           catch (IOException e)
           {
               try{
                   channel.basicRecover();
                }
               catch (IOException e1)
               {
                  e1.printStackTrace();
               }
           }
    }


}
