/**
 * Project: arithmeticdemo
 * File created at 2020/12/2 19:12
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 功能描述
 *  227. 基本计算器 II   https://leetcode-cn.com/problems/basic-calculator-ii/
 *  此算法的思路很简单，先把乘除法的值计算出来，最终将所有的运算简化成只有加法。
 *
 *  1.先跳过空格
 *  2.出现了数字则记录整个数字是多少，然后根据之前的运算符决定下一步：
 *      如果是加号'+'，说明前面的运算独立于以后的运算，可以将结果暂时放入栈；
 *      如果是减号'-'，可以看成-1 * tempNum，然后将-tempNum入栈；
 *      如果是乘号'*'或者除号'/'，由于前面的运算独立于此，可以先计算lastNum和tempNum积，然后结果入栈。
 *  3.最后将栈中的所有元素相加就是答案。
 *
 * @author linlipeng
 * @version 1.0
 * @type Calculate
 * @date 2020/12/2 19:12
 */
public class Calculate {
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();

        char lastOp = '+';
        // 变成字符数组
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i ++){
            if(arr[i] == ' ') continue;

            if(Character.isDigit(arr[i])){
                // 取出当前字符数字
                int tempNum = arr[i] - '0';
                while(++i < arr.length && Character.isDigit(arr[i])){
                    // 拼接字符数字 （例如42 = 4 * 10 + 2）
                    tempNum = tempNum * 10 + (arr[i] - '0');
                }
                i--;

                if(lastOp == '+') {
                    numStack.push(tempNum);
                }
                else if(lastOp == '-') {
                    numStack.push(-tempNum);
                }
                else {
                    numStack.push(res(lastOp, numStack.pop(), tempNum));
                }
            } else {
                lastOp = arr[i];
            }
        }

        int ans = 0;
        for(int num : numStack) {
            ans += num;
        }
        return ans;
    }

    private int res(char op, int a, int b){
        if(op == '*') return a * b;
        else if(op == '/') return a / b;
        else if(op == '+') return a + b; //其实加减运算可以忽略
        else return a - b;
    }
}
