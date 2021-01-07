package com.peter; /**
 * Project: spring-boot-01-helloworld
 * File created at 2021/1/4 16:09
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
import com.peter.controller.FB;
import com.peter.controller.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type Test
 * @date 2021/1/4 16:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Testxx {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FB fb;

    @Autowired
    private User user;

    @Test
    public void test() {
        System.out.println("xxxx");
//        System.out.println(fb);
        Object bean = applicationContext.getBean("&FB");
        System.out.println(bean);
        Object fb = applicationContext.getBean("FB");
        System.out.println(fb);
        System.out.println(user.getName());

    }
}
