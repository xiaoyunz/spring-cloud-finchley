package com.zhouxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin.server.internal.EnableZipkinServer;


/**
 * spring Cloud为Finchley版本的时候，已经不需要自己构建Zipkin Server了，只需要下载jar即可，下载地址：
 * https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/
 * 也可以在这里下载： 链接: https://pan.baidu.com/s/1w614Z8gJXHtqLUB6dKWOpQ 密码: 26pf
 * 下载完成jar 包之后，需要运行jar，如下： java -jar zipkin-server-2.10.1-exec.jar
 * 
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServiceApplication.class, args);
	}

}
