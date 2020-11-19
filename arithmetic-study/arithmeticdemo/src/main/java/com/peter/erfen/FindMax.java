/**
 * Project: arithmeticdemo
 * File created at 2020/11/10 16:07
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.erfen;

/**
 * 功能描述
 *      153. 寻找旋转排序数组中的最大值   https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * @author linlipeng
 * @version 1.0
 * @type FindMin
 * @date 2020/11/10 16:07
 */
public class FindMax {
    public static int findMax(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] > nums[right]) {
                // middle可能是最大值（直接在此区间查找left-middle）
                right = middle;
            } else {
                // middle肯定不是最大值（直接在另一区间查找middle+1 - right）
                left = middle + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] num = new int[] {
                3,4,5,1,2
//                4,5,6,7,0,1,2
        };
        System.out.println(findMax(num));
    }
}
