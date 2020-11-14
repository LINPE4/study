/**
 * Project: arithmeticdemo
 * File created at 2020/11/14 12:46
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.dp;

import java.util.Arrays;

/**
 * 功能描述
 *    213. 打家劫舍 II   https://leetcode-cn.com/problems/house-robber-ii/description/
 * @author linlipeng
 * @version 1.0
 * @type Rob
 * @date 2020/11/14 12:46
 */
public class Rob2 {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private int myRob(int[] nums) {
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
