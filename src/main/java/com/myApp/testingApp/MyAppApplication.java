package com.myApp.testingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);
	}

}
