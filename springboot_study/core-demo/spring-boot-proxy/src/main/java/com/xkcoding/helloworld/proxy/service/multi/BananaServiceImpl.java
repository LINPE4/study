/**
 * Project: spring-boot-demo-helloworld
 * File created at 2020/7/8 11:44
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.proxy.service.multi;

import com.xkcoding.helloworld.proxy.service.FruitsService;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type BananaServiceImpl
 * @date 2020/7/8 11:44
 */
public class BananaServiceImpl implements FruitsService {
    @Override
    public String desc(Integer num) {
        return "banana num: " + num;
    }
}
