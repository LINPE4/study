/**
 * Project: arithmeticdemo
 * File created at 2020/11/4 19:39
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *  51. N 皇后      https://leetcode-cn.com/problems/n-queens/
 * @author linlipeng
 * @version 1.0
 * @type SolveNQueens
 * @date 2020/11/4 19:39
 */
public class SolveNQueens {
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        int board[][] = new int[n][n];
        dfs(0, board, n);
        return result;
    }

    private void dfs(int row, int[][] board, int n) {
        if (row == n) {
            result.add(transfer(board, n));
            return;
        }
        /**
         * 从列开始遍历，即先在 board[0][0]（第一行第一列）放一个皇后，
         *  然后从第二行开始遍历，直到第n行，假如能放入n个皇后，则为结果输出。
         *  假如无法满足放入皇后的条件，则一直回溯，再重新放置皇后。
         *  如：board[0][0]放置皇后之后，其他行无法满足条件，此时会回溯到board[0][0],
         *      然后重新在board[0][1]放置皇后，再遍历。
         */
        for (int col = 0; col < n; col++) {
            if (canPut(board, n, row, col)) {
                board[row][col] = 1;
                dfs(row +1, board, n);
                board[row][col] = 0;
            }

        }
    }

    private boolean canPut(int[][] board, int n, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }
        for (int i = col - 1; i >=0; i--) {
            if (row-col + i  < 0) {
                break;
            }
            if (board[row-col + i ][i] == 1) {
                return false;
            }
        }
        for (int i = col + 1; i < n; i++) {
            if (row + col - i  < 0) {
                break;
            }
            if (board[row + col - i ][i] == 1) {
                return false;
            }
        }
        return true;
    }

    private List<String> transfer(int[][] board, int n) {
        List<String> result = new ArrayList<>();
        for (int row = 0; row < n; row ++) {
            StringBuilder str = new StringBuilder();
            for (int col = 0; col < n; col ++) {
                if (board[row][col] == 1) {
                    str.append("Q");
                } else {
                    str.append(".");
                }
            }
            result.add(str.toString());
        }
        return result;
    }
}
