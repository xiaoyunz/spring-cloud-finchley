package com.zhouxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;
/**
 * 断路器监控(Hystrix Dashboard)
 * 查看Hystrix.stream
 * http://192.168.14.127:8764/actuator/hystrix.stream
 * 查看Hystrix Dashboard图形展示 http://192.168.14.127:8764/hystrix
 * 在界面依次输入：http://192.168.14.127:8764/actuator/hystrix.stream 、2000 、zhouxy 
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class RibbonFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonFeignClientApplication.class, args);
	}
	
	/**
	 * 使用 RestTemplate + Ribbon负载均衡调用服务
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * zipkin服务链路追踪采集器
	 * @return
	 */
	@Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
