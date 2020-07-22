/**
 * Project: arithmeticdemo
 * File created at 2020/7/22 15:07
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.sort;

/**
 * 功能描述
 *      冒泡排序
 * @author linlipeng
 * @version 1.0
 * @type BubbleSort
 * @date 2020/7/22 15:07
 */
public class BubbleSort {

    public int[] sort(int[] sourceArray) throws Exception {
        for (int i = 0; i < sourceArray.length; i++) {
            for (int j = i; j < sourceArray.length - i; j++) {
                // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
                boolean flag = true;
                if (sourceArray[j] > sourceArray[j+1]) {
                    int temp = sourceArray[j];
                    sourceArray[j] = sourceArray[j+1];
                    sourceArray[j+1] = temp;
                    flag = false;
                }
                if (flag) {
                    break;
                }
            }
        }
        return sourceArray;
    }
}
