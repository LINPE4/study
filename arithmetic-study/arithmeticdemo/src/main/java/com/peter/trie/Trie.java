/**
 * Project: arithmeticdemo
 * File created at 2020/11/14 14:13
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.trie;

/**
 * 功能描述
 *    208. 实现 Trie (前缀树)  https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * @author linlipeng
 * @version 1.0
 * @type Trie
 * @date 2020/11/14 14:13
 */
public class Trie {
    public boolean isWord = false;
    public String word = null;
    public Trie[] children = new Trie[26];
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie root=this;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.children[chars[i]-'a'] == null) {
                root.children[chars[i]-'a'] = new Trie();
            }
            root = root.children[chars[i]-'a'];
        }
        root.isWord = true;
        root.word = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie root=this;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.children[chars[i]-'a'] == null) {
                return false;
            }
            root = root.children[chars[i]-'a'];
        }
        return root.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie root=this;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.children[chars[i]-'a'] == null) {
                return false;
            }
            root = root.children[chars[i]-'a'];
        }
        return true;
    }
}
