package com.szaruga.InternetBankingApplicationDemo;

import com.szaruga.InternetBankingApplicationDemo.configuration.WebClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebClientConfig.class)
public class InternetBankingApplicationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetBankingApplicationDemoApplication.class, args);
	}

}
