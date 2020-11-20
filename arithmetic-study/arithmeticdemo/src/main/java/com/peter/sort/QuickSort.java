/**
 * Project: arithmeticdemo
 * File created at 2020/7/22 15:42
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.sort;

/**
 * 功能描述
 *      快速排序
 *  该方法的基本思想是：
 *          1．先从数列中取出一个数作为基准数。
 *          2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 *          3．再对左右区间重复第二步，直到各区间只有一个数。
 * @author linlipeng
 * @version 1.0
 * @type QuickSort
 * @date 2020/7/22 15:42
 */
public class QuickSort {

    public int[] sort(int[] sourceArray) throws Exception {
        quickSort(sourceArray, 0, sourceArray.length -1);
        return sourceArray;
    }

    private void quickSort(int[] sourceArray, int left, int right) {
        if (left < right) {
            // 取到中间下标
            int index = getIndex(sourceArray, left, right);
            // 以区间left，index-1 重新排序
            quickSort(sourceArray, left, index - 1);
            // 以区间index+1，right 重新排序
            quickSort(sourceArray, index + 1, right);
        }
    }

    private int getIndex(int[] sourceArray, int left, int right) {
        int temp = sourceArray[left];
        while (left < right) {
            while (left < right && sourceArray[right] >= temp) {
                right --;
            }
            sourceArray[left] = sourceArray[right];
            while (left < right && sourceArray[left] <= temp) {
                left ++;
            }
            sourceArray[right] = sourceArray[left];
        }
        sourceArray[left] = temp;
        return left;
    }

    public static void main(String[] args) {
//        int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
        int[] arr = { 1, 2,3 };
        QuickSort q = new QuickSort();
        q.quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
