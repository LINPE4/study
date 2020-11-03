/**
 * Project: arithmeticdemo
 * File created at 2020/11/3 19:40
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *  78. 子集    https://leetcode-cn.com/problems/subsets/
 * @author linlipeng
 * @version 1.0
 * @type Subsets
 * @date 2020/11/3 19:40
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
       List<List<Integer>> result = new ArrayList<>();
       handle(nums, 0, result, new ArrayList<Integer>());
       return result;
    }

    private void handle(int[] nums, int i, List<List<Integer>> result, List<Integer> temp) {
        result.add(new ArrayList<>(temp));
        for (int j = i; j < nums.length; j++) {
            temp.add(nums[j]);
            handle(nums, j+1, result, temp);
            temp.remove(temp.size() -1);
        }
    }
}
