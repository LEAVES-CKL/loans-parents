package com.lxit.loans.action;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
 
@SpringBootApplication  //开启springboot
@EnableEurekaClient   //开启Eureka配置
public class ApplyLoansClient {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplyLoansClient.class).web(true).run(args);
	}

}
