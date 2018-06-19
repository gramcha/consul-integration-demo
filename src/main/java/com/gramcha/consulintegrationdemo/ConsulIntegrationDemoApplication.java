package com.gramcha.consulintegrationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages= {"com.gramcha.*"})
public class ConsulIntegrationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulIntegrationDemoApplication.class, args);
	}
}
