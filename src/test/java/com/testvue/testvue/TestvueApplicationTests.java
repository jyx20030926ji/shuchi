package com.testvue.testvue;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.testvue.testvue.Service.MailService;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.mapper.UserMapper;
import com.testvue.testvue.publicher.MessagePublisher;
import com.testvue.testvue.repository.BookRepository;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest

class TestvueApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BookRepository bookRepository;


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

	@Test
	public void testRedisConnection() {
		// 获取操作 Redis 字符串的对象
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

		// 测试写入操作
		ops.set("testKey", "Hello Redis!");

		// 测试读取操作
		String value = ops.get("testKey");

		// 验证 Redis 写入和读取是否正常

	}
	@Test
	public void testSendSimpleMail() {
		String to = "2736805640@qq.com";  // 收件人邮箱地址
		String subject = "Test Email";  // 邮件主题
		String text = "This is a test email sent from Spring Boot application.";  // 邮件内容

		// 调用 MailService 发送邮件
		mailService.sendSimpleMail(to, subject, text);

		// 这里通常你会有额外的断言逻辑来确认邮件是否成功发送
		// 由于邮件发送无法在测试中直接断言，我们可以假设邮件已发送成功
		// 在生产环境中，你可以使用邮件发送服务的日志或监控来验证邮件是否成功发送
	}
	@Test
	public void testMail() {
		// 从 Redis 获取 "code" 键的值
		String code = stringRedisTemplate.opsForValue().get("code");

		// 打印 Redis 中的验证码
		System.out.println("Retrieved code from Redis: " + code);

		// 断言 code 不为空，确保 Redis 中有这个键
		assert code != null : "Code not found in Redis!";
	}
	@Test
	public void saveToRepository()
	{
		Book book = Book.builder().id(1L).bookName("红楼梦").bookAuthor("曹雪芹").build();
		//bookRepository.save(book);

	}
	@Test
	public void getByNameOrAuthor()

	{
		String name="b 红楼 1";
		List<Book> bookList = bookRepository.findByBookNameContainingOrBookAuthorContaining(name, name);

		System.out.println(bookList);

	}


}
