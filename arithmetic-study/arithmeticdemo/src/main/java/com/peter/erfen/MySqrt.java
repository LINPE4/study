/**
 * Project: arithmeticdemo
 * File created at 2020/11/9 19:23
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.erfen;

/**
 * 功能描述
 *    69. x 的平方根   https://leetcode-cn.com/problems/sqrtx/
 * @author linlipeng
 * @version 1.0
 * @type MySqrt
 * @date 2020/11/9 19:23
 */
public class MySqrt {
    public int mySqrt(int x) {
        int min = 0;
        int max = x;
        int result = -1;
        while (min <= max) {
            int midd = min + (max - min) / 2;
            if ((long) midd * midd <= x) {
                result = midd;
                min = midd + 1;
            } else {
                max = midd - 1;
            }
        }
        return result;

    }
}
