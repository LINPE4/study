/**
 * Project: arithmeticdemo
 * File created at 2020/11/10 11:28
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *   74. 搜索二维矩阵   https://leetcode-cn.com/problems/search-a-2d-matrix/
 *
 *      1   3   5   7
 *      10  11  16  20
 *      23  30  34   50
 * @author linlipeng
 * @version 1.0
 * @type SearchMatrix
 * @date 2020/11/10 11:28
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) {
            return false;
        }
        int rows = 0;
        int col = matrix[0].length-1;
        while(rows >= 0 && rows < matrix.length
                && col >= 0 && col < matrix[0].length){
            if(matrix[rows][col]==target) {
                return true;
            }
            if(matrix[rows][col]>target) {
                col--;
            }else{
                rows++;
            }
        }
        return false;

    }
}
