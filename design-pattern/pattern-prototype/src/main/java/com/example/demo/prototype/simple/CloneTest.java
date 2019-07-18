package com.example.demo.prototype.simple;

import java.util.ArrayList;

/**
 * Created by Tom on 2018/3/7.
 * 实现Cloneable接口只能实现浅拷贝
 */
public class CloneTest {

    public static void main(String[] args) {

        CloneTarget p = new CloneTarget();
        p.name = "Tom";
//        p.list = new ArrayList<CloneTarget>();
//        p.list.add(new CloneTarget());
        p.target = new CloneTarget();
        System.out.println(p.target);

        try {
            CloneTarget obj =  (CloneTarget) p.clone();
            System.out.println(obj.target);

            System.out.println(p == obj);
            System.out.println(p.target == obj.target);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
