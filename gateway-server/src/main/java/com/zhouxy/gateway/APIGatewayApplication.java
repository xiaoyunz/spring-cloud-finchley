package com.zhouxy.gateway;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import com.zhouxy.gateway.filter.ExecuteTimeGatewayFilter;
import com.zhouxy.gateway.filter.GatewayRateLimitFilterByCpu;
import com.zhouxy.gateway.filter.GatewayRateLimitFilterByIp;
import com.zhouxy.gateway.filter.RemoteAddrKeyResolver;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class APIGatewayApplication {
	
	/**
     * 程序主入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class, args);
    }
    
    /**
     * 统计某个或者某种路由的的处理时长
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator ExecuteTimeRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/test")
                        .filters(f -> f.filter(new ExecuteTimeGatewayFilter()))
                        .uri("http://localhost:8001/executTimeFilter?name=zhouxy")
                        .order(0)
                        .id("execut_time_filter")
                )
                .build();
    }

	/**
	 * 自定义过滤器进行限流
	 * @param builder
	 * @return
	 */
    @Bean
    public RouteLocator rateLimitFilterByIpRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/test/rateLimit")
                        .filters(f -> f.filter(new GatewayRateLimitFilterByIp(10,1,Duration.ofSeconds(1))))
                        .uri("http://localhost:8000/hello/rateLimit")
                        .id("rateLimitbyIp_route")
                ).build();
    }
    
    /**
     * Redis RateLimiter限流
     * @return
     */
    @Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }
    
    /**
     * 根据CPU的使用情况限流
     */
    @Autowired
    private GatewayRateLimitFilterByCpu gatewayRateLimitFilterByCpu;

    @Bean
    public RouteLocator rateLimitFilterByCpuRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/test/rateLimit")
                        .filters(f -> f.filter(gatewayRateLimitFilterByCpu))
                        .uri("http://localhost:8000/hello/rateLimit")
                        .id("rateLimitbyCpu_route")
                ).build();
    }
    

}
