/**
 * Project: arithmeticdemo
 * File created at 2020/12/9 17:06
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.string;

/**
 * 功能描述
 *      125. 验证回文串 https://leetcode-cn.com/problems/valid-palindrome/
 * @author linlipeng
 * @version 1.0
 * @type IsPalindrome
 * @date 2020/12/9 17:06
 */
public class IsPalindrome {

    public boolean isPalindrome(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
        return sgood.toString().equals(sgood_rev.toString());
    }
}
