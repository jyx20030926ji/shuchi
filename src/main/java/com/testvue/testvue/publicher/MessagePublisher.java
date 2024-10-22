package com.testvue.testvue.publicher;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessagePublisher {


   @Autowired
   private RabbitTemplate rabbitTemplate;

 public void sendMessageToQueue10(String message)
 {
  rabbitTemplate.convertAndSend("topic-jyx10","topic10.5",message);
 }
 public void sendMessageToQueue11(String message)
 {
  rabbitTemplate.convertAndSend("topic-jyx10","queue11",message);
 }


}
