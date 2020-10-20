/**
 * Project: arithmeticdemo
 * File created at 2020/10/20 19:07
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *      11. 盛最多水的容器 :　https://leetcode-cn.com/problems/container-with-most-water/　
 * @author linlipeng
 * @version 1.0
 * @type MaxArea
 * @date 2020/10/20 19:07
 */
public class MaxArea {

    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length -1;
        while (i < j) {
            maxArea = height[i] < height[j]
                    ? Math.max(height[i ++] * (j - i), maxArea)
                    : Math.max(height[j --] * (j - i), maxArea);
        }
        return maxArea;
    }
}
