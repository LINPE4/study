/**
 * Project: spring-boot-demo
 * File created at 2020/7/4 14:36
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.proxy.service.impl;

import com.xkcoding.helloworld.proxy.service.PeterService;
import org.springframework.stereotype.Service;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type PeterService
 * @date 2020/7/4 14:36
 */
@Service("peter")
public class PeterServiceImpl implements PeterService {

    @Override
    public int test(String name) {
        System.out.println("test name: " + name);
        return 0;
    }
}
