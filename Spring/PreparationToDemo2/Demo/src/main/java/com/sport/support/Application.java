package com.sport.support;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		TimeZone tzone = TimeZone.getTimeZone("UTC+3");
		TimeZone.setDefault(tzone);
        SpringApplication.run(Application.class, args);
    }

}