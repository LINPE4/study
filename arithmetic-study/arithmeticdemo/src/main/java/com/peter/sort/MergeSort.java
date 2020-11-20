/**
 * Project: arithmeticdemo
 * File created at 2020/7/23 18:37
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.sort;

import java.util.Arrays;

/**
 * 功能描述
 *      归并排序
 *      https://blog.csdn.net/WinstonLau/article/details/99898913
 * @author linlipeng
 * @version 1.0
 * @type MergeSort
 * @date 2020/7/23 18:37
 */
public class MergeSort {

    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};

        mergeSort(arr, 0, arr.length - 1);
        System.out.println("排序后的结果为：" + Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right);
            //合并
            merge(arr, left, mid, right);
        }
    }

    /**
     * 合并
     * @param arr 原数组
     * @param left 数组左下标
     * @param mid 数组中间坐标
     * @param right 数组右下标
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int tempIndex = 0;
        // 新建数组，用来存储合并排好序的新数组
        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            // 从小到大依次塞进新数组 （此时要不左半区没塞满要不右半区没塞满）
            if (arr[i] <= arr[j]) {
                temp[tempIndex++] = arr[i++];
            } else {
                temp[tempIndex++] = arr[j++];
            }
        }
        // 假如左半区没塞满，将剩下的塞进新数组
        while (i<= mid) {
            temp[tempIndex++] = arr[i++];
        }
        // 假如右半区没塞满，将剩下的塞进新数组
        while (j<= right) {
            temp[tempIndex++] = arr[j++];
        }
        // 将排好序的新数组按照所在原数组的位置重新赋值到原数组（left即为原数组下标开始位置）
        for (int k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }

}
