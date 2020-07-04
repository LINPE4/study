/**
 * Project: spring-boot-demo
 * File created at 2020/7/3 18:04
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.proxy.service.proxy;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 接口实例工厂，这里主要是用于提供接口的实例对象
 * @author lichuang
 * @param <T>
 */
public class ServiceImplProxyFactory<T> implements FactoryBean<T> {

    private Object object;

    public ServiceImplProxyFactory(Object object) {
        this.object = object;
    }

    @Override
    public T getObject() throws Exception {
        //这里主要是创建接口对应的实例，便于注入到spring容器中
        InvocationHandler handler = new ServiceImplProxy<>(object);
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),
            object.getClass().getInterfaces(),handler);
    }

    @Override
    public Class<T> getObjectType() {
        return (Class<T>) object.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
