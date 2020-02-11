package cn.enjoyedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketWithSpringbootApplication {

    public static void main(String[] args) {
        //默认情况下，启动Tomcat(8080)
        SpringApplication.run(RocketWithSpringbootApplication.class, args);
    }
}
