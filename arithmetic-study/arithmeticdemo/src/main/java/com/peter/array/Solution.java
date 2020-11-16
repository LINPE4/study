/**
 * Project: arithmeticdemo
 * File created at 2020/11/16 10:49
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

import java.util.Random;

/**
 * 功能描述
 *      384. 打乱数组   https://leetcode-cn.com/problems/shuffle-an-array/
 * @author linlipeng
 * @version 1.0
 * @type Solution
 * @date 2020/11/16 10:49
 */
public class Solution {
    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return original;
    }

    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }

}
