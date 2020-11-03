/**
 * Project: arithmeticdemo
 * File created at 2020/11/3 18:57
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.recursion;

/**
 * 功能描述
 *   50. Pow(x, n)  https://leetcode-cn.com/problems/powx-n/
 * @author linlipeng
 * @version 1.0
 * @type MyPow
 * @date 2020/11/3 18:57
 */
public class MyPow {
    public double myPow(double x, int n) {
        return handle(x, n);
    }

    private double handle(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double result = handle(x, n / 2);
        return n % 2 == 0 ? result * result : result * result * x;
    }


}
