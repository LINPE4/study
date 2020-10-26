/**
 * Project: spring-boot-demo
 * File created at 2020/7/3 17:36
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.beanRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type MyProxy
 * @date 2020/7/3 17:36
 */
public class MyProxy implements InvocationHandler {

    private Class<?> interfaceClass;

    public Object bind(Class<?> cls) {
        this.interfaceClass = cls;
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[] {interfaceClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("hello world");
//        Object ret = method.invoke(this, args);
//        System.out.println("end");
//        return ret;
        return "hello world";
    }

}
