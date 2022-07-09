package com.andrea.galatolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackageClasses = HandlerController.class)
//@ComponentScan(basePackageClasses = HandlerService.class)
public class JsonMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonMakerApplication.class, args);
	}

}
