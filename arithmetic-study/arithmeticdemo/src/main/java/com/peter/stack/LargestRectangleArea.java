/**
 * Project: arithmeticdemo
 * File created at 2020/10/24 13:53
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.stack;

/**
 * 功能描述
 *    84. 柱状图中最大的矩形   https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @author linlipeng
 * @version 1.0
 * @type LargestRectangleArea
 * @date 2020/10/24 13:53
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
}
