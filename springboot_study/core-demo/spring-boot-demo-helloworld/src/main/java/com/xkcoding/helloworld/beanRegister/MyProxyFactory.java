/**
 * Project: spring-boot-demo
 * File created at 2020/7/3 17:38
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.beanRegister;

import org.springframework.beans.factory.FactoryBean;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type MyProxyFactory
 * @date 2020/7/3 17:38
 */
public class MyProxyFactory<T> implements FactoryBean<T> {

    private Class<T> interfaceClass;
    public Class<T> getInterfaceClass() {
        return interfaceClass;
    }
    public void setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
    @Override
    public T getObject() throws Exception {
        return (T) new MyProxy().bind(interfaceClass);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        // 单例模式
        return true;
    }

}
