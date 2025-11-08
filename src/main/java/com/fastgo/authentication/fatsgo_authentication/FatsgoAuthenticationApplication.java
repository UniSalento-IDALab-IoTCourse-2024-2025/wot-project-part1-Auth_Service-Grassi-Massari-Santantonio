package com.fastgo.authentication.fatsgo_authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FatsgoAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FatsgoAuthenticationApplication.class, args);
	}

}
