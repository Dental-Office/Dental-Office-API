package com.dentaloffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DentalofficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentalofficeApplication.class, args);
	}

}
