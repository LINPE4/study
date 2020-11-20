/**
 * Project: arithmeticdemo
 * File created at 2020/7/22 13:50
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.sort;

/**
 * 功能描述
 *      插入排序
 *      题解：
 *          从第一个元素开始，该元素可以认为已经被排序
 *          取出下一个元素，在已经排序的元素序列中从后向前扫描
 *          如果该元素（已排序）大于新元素，将该元素移到下一位置
 *          重复步骤 3，直到找到已排序的元素小于或者等于新元素的位置
 *          将新元素插入到该位置后
 *          重复步骤 2~5
 *      以 arr[] = [5，4,3,2,1] 为例子
 *      首先 index = 1,int temp = 4； arr[0] > temp , 即将arr[1] = arr[0], 此时数组为 arr[] = [5,5,3,2,1], 此时存在交换的情况，并且最后的下标为0，把temp赋值给arr[0]. 此时数组为
 *      [4,5,3,2,1]
 *      第二步同理，index = 2， int temp = 3; arr[1] > temp, 即将arr[2] = arr[1], 此时数组为 arr[] = [4,5,5,2,1]; arr[0] > temp ,即将arr[0] = arr[1], 此时数组为 arr[] = [4,4,5,2,1]
 *      此时存在交换的情况，并且最后的下标为0，把temp赋值给arr[0]. 此时数组为[3,4,5,2,1]
 *
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
