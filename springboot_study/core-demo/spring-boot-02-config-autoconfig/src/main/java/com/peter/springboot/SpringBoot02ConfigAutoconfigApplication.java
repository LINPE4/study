package com.peter.springboot;

import com.peter.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SpringBoot02ConfigAutoconfigApplication {

	@Autowired
	private HelloService helloService;
	@RequestMapping("/index")
	@ResponseBody
	public String index() {

		System.out.println(helloService.getMsg());
		return helloService.sayHello();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02ConfigAutoconfigApplication.class, args);
	}
}
