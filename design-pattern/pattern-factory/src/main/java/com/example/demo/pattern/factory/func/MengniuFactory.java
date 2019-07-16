package com.example.demo.pattern.factory.func;


import com.example.demo.pattern.factory.Mengniu;
import com.example.demo.pattern.factory.Milk;

/**
 * Created by Tom on 2018/3/4.
 */
public class MengniuFactory implements  Factory {


    @Override
    public Milk getMilk() {
        return new Mengniu();
    }
}
