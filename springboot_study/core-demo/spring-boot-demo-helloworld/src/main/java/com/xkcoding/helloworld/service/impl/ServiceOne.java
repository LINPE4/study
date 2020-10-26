/**
 * Project: spring-boot-demo
 * File created at 2020/7/2 11:23
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.service.impl;

import com.xkcoding.helloworld.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type ServiceOne
 * @date 2020/7/2 11:23
 */
//@Service
public class ServiceOne implements BaseService {

    @Override
    public String test(String a) {
        System.out.println("a: " + a);
        return "bb";
    }
}
