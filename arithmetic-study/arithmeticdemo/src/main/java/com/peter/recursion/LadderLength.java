/**
 * Project: arithmeticdemo
 * File created at 2020/11/7 14:37
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 功能描述
 * 单向BFS
 * 	 * 		从beginword出发，依次遍历beginword的所有字母，（例如'hit', 第一个字母为h，依次匹配出 ait，bit，... zit）,
 * 	 * 	找出wordList中能与之匹配的单词，存入queue（以便存入的进行下一次的寻找）
 * 	 * 	同理（第二个字母为i， 依次匹配出hat, hbt,...hzt）,
 * 	 * 	找出wordList中能与之匹配的单词，存入queue（以便存入的进行下一次的寻找）.
 * 	 * 	这样第一个单词都找出了能匹配的单词（存在queue，步数+1），然后再从queue中取出，再找到下轮匹配到的单词，再入queue，步数+1，
 * 	 * 	直到最终匹配到endword.
 *
 *   127. 单词接龙     https://leetcode-cn.com/problems/word-ladder/
 * @author linlipeng
 * @version 1.0
 * @type LadderLength
 * @date 2020/11/7 14:37
 */
public class LadderLength {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (wordList == null || !wordList.contains(endWord)) {
            return 0;
        }
        int step = 1;
        Queue<String> queue = new LinkedList();
        queue.add(beginWord);
        List<String> visted = new ArrayList<>();
        visted.add(beginWord);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] toCharArray = word.toCharArray();
                for (int j = 0; j < toCharArray.length; j++) {
                    char temp = toCharArray[j];
                    for (char k = 'a'; k < 'z'; k++) {
                        if (k == temp) {
                            continue;
                        }
                        toCharArray[j] = k;
                        String matchWord = new String(toCharArray);
                        if (endWord.equals(matchWord)) {
                            return step + 1;
                        }
                        if (words.contains(matchWord) && !visted.contains(matchWord)) {
                            queue.add(matchWord);
                            visted.add(matchWord);
                        }
                    }
                    toCharArray[j] = temp;
                }
            }
            step ++;
        }
        return 0;
    }
}
