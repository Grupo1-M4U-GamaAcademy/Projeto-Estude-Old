package com.m4ugrupo1.m4ugrupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.sumit" })
@EnableTransactionManagement
@EntityScan(basePackages = { "com.sumit" })
@ComponentScan(basePackages = { "com.sumit" })
public class M4ugrupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(M4ugrupo1Application.class, args);
	}

}
