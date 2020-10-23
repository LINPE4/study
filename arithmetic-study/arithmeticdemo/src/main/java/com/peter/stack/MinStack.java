/**
 * Project: arithmeticdemo
 * File created at 2020/10/23 14:33
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.stack;

import java.util.Stack;

/**
 * 功能描述
 *     155. 最小栈   https://leetcode-cn.com/problems/min-stack/
 * @author linlipeng
 * @version 1.0
 * @type MinStack
 * @date 2020/10/23 14:33
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            Integer top = minStack.peek();
            if (x <= top) {
                minStack.push(top);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        if (pop == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
