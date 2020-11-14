/**
 * Project: arithmeticdemo
 * File created at 2020/11/14 12:46
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.dp;

/**
 * 功能描述
 *
 *    198. 打家劫舍        https://leetcode-cn.com/problems/house-robber/
 *    方法一：动态规划 + 滚动数组
 *     dp[i]=max(dp[i−2]+nums[i],dp[i−1])
 * @author linlipeng
 * @version 1.0
 * @type Rob
 * @date 2020/11/14 12:46
 */
public class Rob {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i-2]+ nums[i], dp[i-1]);
        }
        return dp[length -1];
    }
}
