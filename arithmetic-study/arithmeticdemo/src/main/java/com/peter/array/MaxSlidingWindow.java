/**
 * Project: arithmeticdemo
 * File created at 2020/10/24 14:54
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *    239. 滑动窗口最大值   https://leetcode-cn.com/problems/sliding-window-maximum/
 * @author linlipeng
 * @version 1.0
 * @type MaxSlidingWindow
 * @date 2020/10/24 14:54
 */
public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int slideLength = nums.length - k + 1;
        int[] result = new int[slideLength];
        for (int i = 0; i < slideLength; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            nums[i] = max;
        }
        return result;
    }
}
