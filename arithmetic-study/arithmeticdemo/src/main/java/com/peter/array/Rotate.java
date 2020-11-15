/**
 * Project: arithmeticdemo
 * File created at 2020/11/15 15:51
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *    189. 旋转数组   https://leetcode-cn.com/problems/rotate-array/
 * @author linlipeng
 * @version 1.0
 * @type Rotate
 * @date 2020/11/15 15:51
 */
public class Rotate {
    public void rotate(int[] nums, int k) {
//        int pre = 0;
//        int temp = 0;
//        for (int i = 0; i < k; i++) {
//            pre = nums[nums.length - 1];
//            for (int j = 0; j < nums.length; j++) {
//                temp = nums[j];
//                nums[j] = pre;
//                pre = temp;
//            }
//        }

        k %= nums.length;
        reverse(nums, 0, nums.length -1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length -1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
    }
}
