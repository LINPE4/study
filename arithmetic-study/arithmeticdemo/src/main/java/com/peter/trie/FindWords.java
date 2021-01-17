/**
 * Project: arithmeticdemo
 * File created at 2020/11/14 14:36
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *  212. 单词搜索 II  https://leetcode-cn.com/problems/word-search-ii/
 * @author linlipeng
 * @version 1.0
 * @type FindWords
 * @date 2020/11/14 14:36
 */
public class FindWords {
    List<String> result = new ArrayList<>();
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(board, trie, i, j);
            }
        }
        return result;
    }

    private void dfs(char[][] board, Trie trie, int row, int col) {
        if (row < 0 || row > board.length-1 || col < 0 || col > board[0].length -1) {
            return;
        }
        char c = board[row][col];
        if (c == '$' || trie.children[c - 'a'] == null) {
            return;
        }
        trie = trie.children[c-'a'];
        if (trie.word != null) {
            result.add(trie.word);
            trie.word = null;
        }
        board[row][col] = '$';
        dfs(board, trie, row + 1, col);
        dfs(board, trie, row - 1, col);
        dfs(board, trie, row , col + 1);
        dfs(board, trie, row, col -1);
        board[row][col] = c;
    }
}
