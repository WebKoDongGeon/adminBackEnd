package com.kodg.kodgProject1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kodg.kodgProject1")
@MapperScan(basePackages = "com.kodg.kodgProject1")
public class KodgProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(KodgProject1Application.class, args);
	}

}
