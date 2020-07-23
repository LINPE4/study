/**
 * Project: arithmeticdemo
 * File created at 2020/7/23 18:37
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.sort;

import java.util.Arrays;

/**
 * 功能描述
 *      https://blog.csdn.net/WinstonLau/article/details/99898913
 * @author linlipeng
 * @version 1.0
 * @type MergeSort
 * @date 2020/7/23 18:37
 */
public class MergeSort {

    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length]; //归并排序需要一个额外空间

        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后的结果为：" + Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int tempIndex = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[tempIndex++] = arr[i++];
            } else {
                temp[tempIndex++] = arr[j++];
            }
        }

        while (i<= mid) {
            temp[tempIndex++] = arr[i++];
        }
        while (j<= right) {
            temp[tempIndex++] = arr[j++];
        }
        tempIndex = 0;
        while (left <= right) {
            arr[left++] = temp[tempIndex++];
        }
    }


}
