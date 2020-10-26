package com.xkcoding.helloworld.beanRegister.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LisonCompany<T> implements InvocationHandler {
	
	//被代理的对象
	private Class<T> factory;
	

	public Object getFactory() {

		return factory;
	}


    public void setFactory(Class<T> factory) {
        this.factory = factory;
    }

    //通过Proxy获取动态代理的对象
	public Object getProxyInstance(){
//		return Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), this);
        return Proxy.newProxyInstance(factory.getClassLoader(), new Class[] {factory}, this);
	}



	@Override
	//通过动态代理对象对方法进行增强
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		// 此方式是实例的代理对象使用
		dosomeThingBefore();
		Object ret = method.invoke(proxy, args);
		dosomeThingEnd();
		return ret;

		// 此方式是接口的代理对象使用
//		return "hello world";
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
