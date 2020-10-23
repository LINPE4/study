/**
 * Project: arithmeticdemo
 * File created at 2020/10/23 14:20
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.stack;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 功能描述
 *    20. 有效的括号   https://leetcode-cn.com/problems/valid-parentheses/
 * @author linlipeng
 * @version 1.0
 * @type IsValid
 * @date 2020/10/23 14:20
 */
public class IsValid {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<Character, Character>(){{
            put('(',')'); put('{','}'); put('[',']');
        }};
        Stack<Character> stack = new Stack<Character>(){{
           push('?');
        }};
        for (Character c: s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (c != map.get(stack.pop())) {
                return false;
            }
        }
        return stack.size() == 1;
    }
}
