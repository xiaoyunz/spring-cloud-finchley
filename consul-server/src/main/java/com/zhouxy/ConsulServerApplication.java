package com.zhouxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulServerApplication {

	@RequestMapping("/hi")
	public String home() {
		return "hi ,i'm zhouxy";
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsulServerApplication.class, args);
	}

}
