/**
 * Project: arithmeticdemo
 * File created at 2020/10/26 11:28
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *    242. 有效的字母异位词    https://leetcode-cn.com/problems/valid-anagram/
 * @author linlipeng
 * @version 1.0
 * @type IsAnagram
 * @date 2020/10/26 11:28
 */
public class IsAnagram {

    public boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//        char[] sChars = s.toCharArray();
//        char[] tChars = t.toCharArray();
//        Arrays.sort(sChars);
//        Arrays.sort(tChars);
//        return new String(sChars).equals(new String(tChars));
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (Character c: s.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c,0) + 1);
        }
        for (Character c: t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c,0) + 1);
        }
        for (Character c: s.toCharArray()) {
            if (!sMap.get(c).equals(tMap.getOrDefault(c,0))) {
                return false;
            }
        }
        return true;
    }
}
