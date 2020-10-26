/**
 * Project: arithmeticdemo
 * File created at 2020/10/26 16:10
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.hash;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * 功能描述
 *   49. 字母异位词分组   https://leetcode-cn.com/problems/group-anagrams/
 * @author linlipeng
 * @version 1.0
 * @type GroupAnagrams
 * @date 2020/10/26 16:10
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] toCharArray = str.toCharArray();
            Arrays.sort(toCharArray);
            String sortResult = new String(toCharArray);
            if (map.containsKey(sortResult)) {
                map.get(sortResult).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortResult, list);
            }
        }
        map.forEach((k , v) -> {
            result.add(v);
        });
        return result;
    }

    public static void main(String[] args) {

        List<List<String>> lists = groupAnagrams((String[]) Arrays.asList("eat", "aet", "xx").toArray());
        System.out.println(lists);
    }
}
