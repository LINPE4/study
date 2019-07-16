package com.example.demo.pattern.factory.func;


import com.example.demo.pattern.factory.Milk;
import com.example.demo.pattern.factory.Yili;

/**
 * Created by Tom on 2018/3/4.
 */
public class YiliFactory implements Factory {

    @Override
    public Milk getMilk() {
        return new Yili();
    }
}
