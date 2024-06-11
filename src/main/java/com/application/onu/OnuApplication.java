package com.application.onu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.application.onu.repository"})
public class OnuApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnuApplication.class, args);
	}

}
