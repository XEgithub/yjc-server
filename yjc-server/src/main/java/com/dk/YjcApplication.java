package com.dk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScans({@ComponentScan("com.dk"), @ComponentScan("cn.sourcespro.commons")})
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class YjcApplication {

	public static void main(String[] args) {
		SpringApplication.run(YjcApplication.class, args);
	}
}
