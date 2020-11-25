/**
 * Project: arithmeticdemo
 * File created at 2020/11/25 19:01
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.heap;

import java.util.PriorityQueue;

/**
 * 功能描述
 *    295. 数据流的中位数  https://leetcode-cn.com/problems/find-median-from-data-stream/
 * @author linlipeng
 * @version 1.0
 * @type MedianFinder
 * @date 2020/11/25 19:01
 */
public class MedianFinder {

    private PriorityQueue<Integer> minHeap, maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (maxHeap.size() <= minHeap.size()) {
            minHeap.add(num);
            maxHeap.add(minHeap.remove());
        } else {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            if (minHeap.isEmpty()) return Double.NaN;
            else return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return minHeap.peek();
        }
    }

}
