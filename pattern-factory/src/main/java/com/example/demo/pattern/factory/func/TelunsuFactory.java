package com.example.demo.pattern.factory.func;


import com.example.demo.pattern.factory.Milk;
import com.example.demo.pattern.factory.Telunsu;

/**
 * 事情变得越来越专业
 * Created by Tom on 2018/3/4.
 */
public class TelunsuFactory implements Factory {

    @Override
    public Milk getMilk() {
        return new Telunsu();
    }
}
