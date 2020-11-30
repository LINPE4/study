/**
 * Project: arithmeticdemo
 * File created at 2020/11/30 19:38
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.dp;

import java.util.Arrays;

/**
 * 功能描述
 *      62. 不同路径     https://leetcode-cn.com/problems/unique-paths/
 *
 *      https://leetcode-cn.com/problems/unique-paths/solution/tu-jie-miao-dong-fen-xi-guo-cheng-by-zi-gei-zi-zu/
 * @author linlipeng
 * @version 1.0
 * @type UniquePaths
 * @date 2020/11/30 19:38
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // int[][] num = new int[m][n];
        // // 第一行为1
        // for (int i = 0; i < m; i++) {
        //     num[i][0] = 1;
        // }
        // // 第一列为1
        // for (int i = 0; i < n; i++) {
        // 	num[0][i] = 1;
        // }
        // for (int i = 1; i < m; i++) {
        // 	for (int j = 1; j < n; j++) {
        // 		num[i][j] = num[i-1][j] + num[i][j-1];
        // 	}
        // }
        // return num[m-1][n-1];
        int[] curColumn = new int[n];
        Arrays.fill(curColumn, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                curColumn[j] +=curColumn[j-1];
            }
        }
        return curColumn[n-1];
    }
}
