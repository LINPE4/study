/**
 * Project: arithmeticdemo
 * File created at 2020/7/22 13:50
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.sort;

/**
 * 功能描述
 *      插入排序
 * @author linlipeng
 * @version 1.0
 * @type InsertSort
 * @date 2020/7/22 13:50
 */
public class InsertSort {

    public int[] sort(int[] sourceArray) {
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < sourceArray.length; i++) {
            // 记录要插入的数据
            int temp = sourceArray[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && sourceArray[j - 1] > temp) {
                sourceArray[j]= sourceArray[j - 1];
                j --;
            }
            // 存在比其小的数，插入
            if (j != i) {
                sourceArray[j]= temp;
            }
        }
        return sourceArray;
    }

    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
        InsertSort q = new InsertSort();
        q.sort(arr);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
