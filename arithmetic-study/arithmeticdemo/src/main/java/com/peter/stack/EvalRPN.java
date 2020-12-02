/**
 * Project: arithmeticdemo
 * File created at 2020/12/2 18:56
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 功能描述
 *   150. 逆波兰表达式求值  https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * @author linlipeng
 * @version 1.0
 * @type EvalRPN
 * @date 2020/12/2 18:56
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer p1, p2;
        for (String str: tokens) {
            switch (str) {
                case "+":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 + p1);
                    break;
                case "-":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 - p1);
                    break;
                case "*":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 * p1);
                    break;
                case "/":
                    p1 = stack.pop();
                    p2 = stack.pop();
                    stack.push(p2 / p1);
                    break;
                default:
                    stack.push(Integer.parseInt(str));
                    break;
            }
        }
        return stack.pop();
    }
}
