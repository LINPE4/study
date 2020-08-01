package com.xiangxue.jack;

import com.xiangxue.jack.service.feign.StudentService;
import com.xiangxue.jack.service.feign.TeacherServiceFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication(scanBasePackages = {"com.xiangxue.jack"})
//注册到eureka
@EnableEurekaClient
//开启断路器功能
@EnableCircuitBreaker
//开启feign支持，clients指定哪个类开启feign
@EnableFeignClients(clients = {StudentService.class, TeacherServiceFeign.class})
public class MicroWebApplication {

    @Bean
    //负载均衡注解
    @LoadBalanced
    RestTemplate restTemplate() {
       return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroWebApplication.class,args);
    }
}
