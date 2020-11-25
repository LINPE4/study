/**
 * Project: arithmeticdemo
 * File created at 2020/11/25 15:20
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.stack;

/**
 * 功能描述
 *   215. 数组中的第K个最大元素  https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @author linlipeng
 * @version 1.0
 * @type FindKthLargest
 * @date 2020/11/25 15:20
 */
public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        buildMaxHeap(nums, len);
        for (int i = nums.length -1; i >= nums.length - k + 1 ; i--) {
            swap(nums, 0, i);
            len --;
            heap(nums, 0, len);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int len) {
        for (int i = len/2-1; i >=0 ; i--) {
            heap(nums, i, len);
        }
    }

    private void heap(int[] nums, int i, int len) {
        int left = 2 * i + 1;
        int right = 2* i + 2;
        int maxIndex = i;
        if (left < len && nums[left] > nums[maxIndex]) {
            maxIndex = left;
        }
        if (right < len && nums[right] > nums[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != i) {
            swap(nums, i, maxIndex);
            heap(nums, maxIndex, len);
        }
    }

    private void swap(int[] nums, int i, int maxIndex) {
        int temp = nums[i];
        nums[i] = nums[maxIndex];
        nums[maxIndex] = temp;
    }
}
