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

    public int[] sort(int[] sourceArray) {
        for (int i = 1; i < sourceArray.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = 0; j < sourceArray.length - i; j++) {
                if (sourceArray[j] > sourceArray[j+1]) {
                    int temp = sourceArray[j];
                    sourceArray[j] = sourceArray[j+1];
                    sourceArray[j+1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return sourceArray;
    }

    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
        BubbleSort q = new BubbleSort();
        q.sort(arr);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
