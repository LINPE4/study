/**
 * Project: spring-boot-demo
 * File created at 2020/7/3 18:05
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.proxy.service.proxy;


import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理，代理对象是类
 * @author lichuang
 */

public class SubServiceProxy<T> implements InvocationHandler {

    private Object object;

    public SubServiceProxy(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        dosomeThingBefore();
        Object ret = method.invoke(object, args);
        dosomeThingEnd();
        return ret;
    }

    //售后服务
    private void dosomeThingEnd() {
        System.out.println("精美包装，快递一条龙服务！");
    }

    //售前服务
    private void dosomeThingBefore() {
        System.out.println("根据您的需求，进行市场调研和产品分析！");

    }
}
