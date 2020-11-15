/**
 * Project: arithmeticdemo
 * File created at 2020/11/15 15:44
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

import java.util.Arrays;

/**
 * 功能描述
 *   169. 多数元素  https://leetcode-cn.com/problems/majority-element/
 * @author linlipeng
 * @version 1.0
 * @type MajorityElement
 * @date 2020/11/15 15:44
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length /2];
    }
}
