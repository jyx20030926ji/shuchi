package com.testvue.testvue;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.testvue.testvue.consumer.MessageConsumer;
import com.testvue.testvue.publicher.MessagePublisher;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class TestvueApplicationTests {


	@Test
	void contextLoads() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		LocalDateTime now = LocalDateTime.now();
		String json = mapper.writeValueAsString(now);
		System.out.println(json); // 应该打印出 ISO 格式的日期时间



	}

     @Autowired
	 private MessagePublisher messagePublisher;





	@Test

	void coverMessage1()
	{
		messagePublisher.sendMessageToQueue10("你好啊queue10");
	}

	@Test
	void coverMessage2()
	{
		messagePublisher.sendMessageToQueue11("你好啊queue11");
	}

}
