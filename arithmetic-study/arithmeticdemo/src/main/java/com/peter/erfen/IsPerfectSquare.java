/**
 * Project: arithmeticdemo
 * File created at 2020/11/9 19:54
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.erfen;

/**
 * 功能描述
 *   367. 有效的完全平方数   https://leetcode-cn.com/problems/valid-perfect-square/
 * @author linlipeng
 * @version 1.0
 * @type IsPerfectSquare
 * @date 2020/11/9 19:54
 */
public class IsPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }
        long min = 0;
        long max = num / 2;
        while (min <= max) {
            long mid = min + (max - min) / 2;
            long sum = mid * mid;
            if (sum == num) {
                return true;
            }
            if (sum < num) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return false;
    }
}
