package com.xkcoding.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * SpringBoot启动类
 * </p>
 *
 * @package: com.xkcoding.helloworld
 * @description: SpringBoot启动类
 * @author: yangkai.shen
 * @date: Created in 2018/9/28 2:49 PM
 * @copyright: Copyright (c)
 * @version: V1.0
 * @modified: yangkai.shen
 */
@SpringBootApplication
@RestController
public class SpringBootDemoHelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoHelloworldApplication.class, args);
	}

}
