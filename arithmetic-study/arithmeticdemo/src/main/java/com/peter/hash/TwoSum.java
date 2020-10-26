/**
 * Project: arithmeticdemo
 * File created at 2020/10/26 16:38
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *    1. 两数之和    https://leetcode-cn.com/problems/two-sum/description/
 * @author linlipeng
 * @version 1.0
 * @type TwoSum
 * @date 2020/10/26 16:38
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
