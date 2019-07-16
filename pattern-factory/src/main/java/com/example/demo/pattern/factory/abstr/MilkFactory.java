package com.example.demo.pattern.factory.abstr;


import com.example.demo.pattern.factory.*;

/**
 * Created by Tom on 2018/3/4.
 */
public class MilkFactory extends  AbstractFactory {


    @Override
    public Milk getMengniu() {
        return new Mengniu();
    }

    @Override
    public Milk getYili() {
        return new Yili();
    }

    @Override
    public Milk getTelunsu() {
        return new Telunsu();
    }

    @Override
    public Milk getSanlu() {
        return new Sanlu();
    }
}
