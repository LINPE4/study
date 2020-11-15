/**
 * Project: arithmeticdemo
 * File created at 2020/11/15 16:22
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

import java.util.Arrays;

/**
 * 功能描述
 *    217. 存在重复元素  https://leetcode-cn.com/problems/contains-duplicate/
 * @author linlipeng
 * @version 1.0
 * @type ContainsDuplicate
 * @date 2020/11/15 16:22
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return true;
            }
        }
        return false;
    }
}
