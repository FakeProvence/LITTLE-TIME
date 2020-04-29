package com.example.little_time;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.little_time.mapper")
public class LittleTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LittleTimeApplication.class, args);
	}



}
