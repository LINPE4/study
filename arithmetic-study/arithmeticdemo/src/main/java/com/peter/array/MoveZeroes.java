/**
 * Project: arithmeticdemo
 * File created at 2020/10/20 19:52
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *      283. 移动零  https://leetcode-cn.com/problems/move-zeroes/
 * @author linlipeng
 * @version 1.0
 * @type MoveZeroes
 * @date 2020/10/20 19:52
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j ++] = nums[i];
            }
        }
        for (int k = j; k < nums.length; k ++) {
            nums[k] = 0;
        }
    }
}
