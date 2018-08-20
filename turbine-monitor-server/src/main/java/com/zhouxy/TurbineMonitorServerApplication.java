package com.zhouxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
/**
 * 断路器聚合监控(Hystrix Turbine)
 * 查看turbine.stream
 * http://192.168.14.127:8766/turbine.stream
 * 查看Hystrix Turbine图形展示 http://192.168.14.127:8766/hystrix
 * 在界面依次输入：http://192.168.14.127:8766/turbine.stream 、2000 、zhouxy 
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@EnableTurbine
public class TurbineMonitorServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TurbineMonitorServerApplication.class, args);
	}

}
