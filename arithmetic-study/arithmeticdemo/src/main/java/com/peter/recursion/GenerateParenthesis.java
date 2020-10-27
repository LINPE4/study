/**
 * Project: arithmeticdemo
 * File created at 2020/10/27 14:07
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *      22. 括号生成  https://leetcode-cn.com/problems/generate-parentheses/
 * @author linlipeng
 * @version 1.0
 * @type GenerateParenthesis
 * @date 2020/10/27 14:07
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(0, 0, n, "", result);
        return result;
    }

    private void generate(int left, int right, int n, String s, List<String> result) {
        if (left == n && right == n) {
            result.add(s);
            return;
        }
        if (left == 0 || left < n) {
            generate(left + 1, right, n, s + "(", result);
        }
        if (right < left) {
            generate(left, right + 1, n, s + ")", result);
        }
    }
}
