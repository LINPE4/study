/**
 * Project: spring-boot-01-helloworld
 * File created at 2021/1/4 14:43
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.controller;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type FB
 * @date 2021/1/4 14:43
 */
@Configuration
public class FB implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setName("peter");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
