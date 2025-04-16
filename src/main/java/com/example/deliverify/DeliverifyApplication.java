package com.example.deliverify;

import com.example.deliverify.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JwtConfig.class)
@SpringBootApplication
public class DeliverifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliverifyApplication.class, args);
	}

}
