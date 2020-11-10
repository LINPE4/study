/**
 * Project: arithmeticdemo
 * File created at 2020/11/10 10:45
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.erfen;

/**
 * 功能描述
 *      33. 搜索旋转排序数组   https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * @author linlipeng
 * @version 1.0
 * @type Search
 * @date 2020/11/10 10:45
 */
public class Search {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                // 如果左半边为有序数组
                if (nums[left] <= target && target < nums[mid]) {
                    // target落在此区间
                    right = mid - 1;
                } else {
                    // target落在另一区间
                    left = mid + 1;
                }
            } else {
                // 如果右半边为有序数组
                if (nums[mid] < target && target <= nums[right]) {
                    // target落在此区间
                    left = mid + 1;
                } else {
                    // target落在另区间
                    right = mid -1;
                }
            }
        }
        return -1;

    }
}
