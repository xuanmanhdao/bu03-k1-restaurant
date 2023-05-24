package com.devbu03.restaurantmanagerment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.devbu03"})
@ComponentScan(basePackages = {"com.devbu03"})
@EnableJpaRepositories(basePackages = {"com.devbu03"})

public class RestaurantManagermentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagermentApplication.class, args);
	}

}
