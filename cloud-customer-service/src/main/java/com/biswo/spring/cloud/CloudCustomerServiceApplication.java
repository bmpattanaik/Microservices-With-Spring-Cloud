package com.biswo.spring.cloud;

import javax.validation.constraints.Digits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages=" com.biswo.spring.cloud")
@EnableDiscoveryClient
public class CloudCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudCustomerServiceApplication.class, args);
	}

}
