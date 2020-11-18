/**
 * Project: arithmeticdemo
 * File created at 2020/11/18 10:17
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *  240. 搜索二维矩阵 II  https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * @author linlipeng
 * @version 1.0
 * @type SearchMatrix
 * @date 2020/11/18 10:17
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        while (row >=0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                row -- ;
            } else {
                col ++;
            }
        }
        return false;
    }
}
