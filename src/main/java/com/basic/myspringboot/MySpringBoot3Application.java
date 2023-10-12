package com.basic.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // 3가지 역할을 함
public class MySpringBoot3Application {

	public static void main(String[] args) {
		//SpringApplication.run(MySpringBoot3Application.class, args);
		SpringApplication application = new SpringApplication(MySpringBoot3Application.class);
		// WebApplication type 변경
		application.setWebApplicationType(WebApplicationType.SERVLET); // 기본 서블릿 베이스 > NONE로 변경할시 tomcat 안뜸
		application.run(args);
	}

}
