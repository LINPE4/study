package com.example.demo.pattern.factory.func;


import com.example.demo.pattern.factory.Milk;
import com.example.demo.pattern.factory.Sanlu;

/**
 * Created by Tom on 2018/3/4.
 */
public class SanluFactory implements  Factory {
    @Override
    public Milk getMilk() {
        return new Sanlu();
    }
}
