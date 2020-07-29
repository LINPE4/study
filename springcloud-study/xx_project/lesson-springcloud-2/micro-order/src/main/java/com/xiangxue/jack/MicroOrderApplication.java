package com.xiangxue.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication

//开启eureka客户端功能
@EnableEurekaClient
public class MicroOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroOrderApplication.class,args);
    }
}
