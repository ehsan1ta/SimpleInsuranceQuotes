package com.example.siqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleInsuranceQuotesAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleInsuranceQuotesAggregatorApplication.class, args);
	}

}
