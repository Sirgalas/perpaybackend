package ru.sergalas.perpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PerpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerpayApplication.class, args);
	}

}
