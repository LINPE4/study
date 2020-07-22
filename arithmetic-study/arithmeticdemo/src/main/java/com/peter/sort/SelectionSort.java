/**
 * Project: arithmeticdemo
 * File created at 2020/7/22 11:42
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.sort;

/**
 * 功能描述
 *      选择排序
 *      从数组下标0开始，依次遍历下标 1 - 数组结尾，将其与下标0的值进行比较， 若小于则替换位置，此时下标0为最小值
 *      同理，从下标1开始，依次遍历下标 2 - 数组结尾，将其与下标1的值进行比较， 若小于则替换位置，此时下标1为 第二最小值
 *      依次循环，直到数据结尾，此时数据便是有序的
 * @author linlipeng
 * @version 1.0
 * @type SelectionSort
 * @date 2020/7/22 11:42
 */
public class SelectionSort {

    public int[] sort(int[] sourceArray) throws Exception {
        for (int i = 0; i < sourceArray.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sourceArray.length; j++) {
                if (sourceArray[j] < sourceArray[i]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                // 说明minIndex被重新赋值，有更小的值
                int temp = sourceArray[i];
                sourceArray[i] = sourceArray[minIndex];
                sourceArray[minIndex] = temp;
            }
        }
        return sourceArray;
    }
}
