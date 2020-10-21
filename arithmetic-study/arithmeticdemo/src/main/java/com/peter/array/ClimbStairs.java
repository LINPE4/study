/**
 * Project: arithmeticdemo
 * File created at 2020/10/21 9:59
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *   70. 爬楼梯       https://leetcode-cn.com/problems/climbing-stairs/
 * @author linlipeng
 * @version 1.0
 * @type ClimbStairs
 * @date 2020/10/21 9:59
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }
}
