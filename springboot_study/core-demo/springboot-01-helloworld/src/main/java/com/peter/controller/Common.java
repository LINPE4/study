/**
 * Project: spring-boot-01-helloworld
 * File created at 2021/1/4 14:49
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type Common
 * @date 2021/1/4 14:49
 */
//@Configuration
public class Common {

    @Bean
    public User user() {
        User user = new User();
        user.setName("peter");
        return user;
    }
}
