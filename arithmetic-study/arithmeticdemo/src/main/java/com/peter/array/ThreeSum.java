/**
 * Project: arithmeticdemo
 * File created at 2020/10/21 10:44
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述
 *       15. 三数之和 https://leetcode-cn.com/problems/3sum/
 *       思路： 先将数组排序，然后遍历有序数组。
 *              三个数初始下标 i， left = i + 1， right = num.length - 1
 *              若 sum == 0 , 则符合。此时 left+1， right - 1
 *              若 sum < 0, 则left + 1
 *              若 sum > 0, 则 right -1
 * @author linlipeng
 * @version 1.0
 * @type ThreeSum
 * @date 2020/10/21 10:44
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left ++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right --;
                    }
                    left ++;
                    right --;
                } else if (sum < 0) {
                    left ++;
                } else {
                    right ++;
                }
            }

        }
        return result;
    }
}
