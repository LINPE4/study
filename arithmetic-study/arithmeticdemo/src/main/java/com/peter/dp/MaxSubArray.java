/**
 * Project: arithmeticdemo
 * File created at 2020/11/12 11:51
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.dp;

/**
 * 功能描述
 *  53. 最大子序和     https://leetcode-cn.com/problems/maximum-subarray/
 *  题解
 *   其实这道题可以这么想： 1.假如全是负数，那就是找最大值即可，因为负数肯定越加越大。
 *   2.如果有正数，则肯定从正数开始计算和，不然前面有负值，和肯定变小了，所以从正数开始。
 *   3.当和小于零时，这个区间就告一段落了，然后从下一个正数重新开始计算(也就是又回到 2 了)。而 dp 也就体现在这个地方。
 * @author linlipeng
 * @version 1.0
 * @type MaxSubArray
 * @date 2020/11/12 11:51
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int sum = 0;
        for (int num: nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            result = Math.max(sum, result);
        }
        return result;
    }
}
