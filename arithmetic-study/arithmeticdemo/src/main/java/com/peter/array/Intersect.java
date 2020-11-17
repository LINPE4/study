/**
 * Project: arithmeticdemo
 * File created at 2020/11/17 19:13
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

import java.util.Arrays;

/**
 * 功能描述
 *    350. 两个数组的交集 II   https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * @author linlipeng
 * @version 1.0
 * @type Intersect
 * @date 2020/11/17 19:13
 */
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int rIndex = 0;
        int nIndex = 0;
        int nIndex2 = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (nIndex < nums1.length && nIndex2 < nums2.length)  {
            if (nums1[nIndex] < nums2[nIndex2]) {
                nIndex ++;
            } else if (nums1[nIndex] > nums2[nIndex2]) {
                nIndex2 ++;
            } else {
                result[rIndex ++] = nums1[nIndex];
                nIndex ++;
                nIndex2 ++;
            }
        }
        return Arrays.copyOfRange(result, 0, rIndex);
    }
}
