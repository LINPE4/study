/**
 * Project: arithmeticdemo
 * File created at 2020/11/12 10:54
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.dp;

import java.util.List;

/**
 * 功能描述
 *   120. 三角形最小路径和   https://leetcode-cn.com/problems/triangle/
 *
 * dp[3][0] = Math.min(dp[4][0],dp[4][1]) + triangle.get(3).get(0);
 * dp[3][1] = Math.min(dp[4][1],dp[4][2]) + triangle.get(3).get(1);
 * dp[3][2]
 * dp[3][3]
 * ...
 *
 * dp[2][0] = Math.min(dp[3][0],dp[3][1]) + triangle.get(2).get(0);
 * dp[2][1] = Math.min(dp[3][1],dp[3][2]) + triangle.get(2).get(1);
 * ...
 *
 * 最后算出 dp[0][0] 即为最优解
 * @author linlipeng
 * @version 1.0
 * @type MinimumTotal
 * @date 2020/11/12 10:54
 */
public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
//        int len = triangle.size();
//        int board[][] = new int[len+1][len+1];
//        for (int i = len -1; i >=0 ; i --) {
//            for (int j = 0; j <= i; j ++) {
//                board[i][j] = Math.min(board[i+1][j], board[i+1][j+1]) + triangle.get(i).get(j);
//            }
//        }
//        return board[0][0];

        int len = triangle.size();
        int[] num = new int[len+1];
        for (int i = len - 1; i >=0 ; i --) {
            for (int j = 0; j < i; j ++) {
                num[j] = Math.min(num[j], num[j+1]) + triangle.get(i).get(j);
            }
        }
        return num[0];
    }
}
