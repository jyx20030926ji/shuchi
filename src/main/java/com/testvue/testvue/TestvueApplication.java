package com.testvue.testvue;




import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.testvue.testvue.mapper")
@SpringBootApplication
public class TestvueApplication {


	public static void main(String[] args) {
		SpringApplication.run(TestvueApplication.class, args);

	}


}
