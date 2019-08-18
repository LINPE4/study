package com.gupaoedu.dubbo;

public class TestMock implements IGpHello{
    @Override
    public String sayHello(String s) {
        return "mock success:" + s;
    }
}
