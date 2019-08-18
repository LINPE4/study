package com.gupaoedu.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App2
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("dubbo-client2.xml");

       //得到IGpHello的远程代理对象

        IGpHello iGpHello = (IGpHello) context.getBean("gpHelloService");

        System.out.println(iGpHello.sayHello("Mic"));

        System.in.read();
    }
}
