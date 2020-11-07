/**
 * Project: arithmeticdemo
 * File created at 2020/11/7 15:22
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tanxin;

/**
 * 功能描述
 *      860. 柠檬水找零  https://leetcode-cn.com/problems/lemonade-change/
 * @author linlipeng
 * @version 1.0
 * @type LemonadeChange
 * @date 2020/11/7 15:22
 */
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five ++;
            } else if (bills[i] == 10) {
                if (five == 0) {
                    return false;
                }
                ten ++;
                five --;
            } else {
                if (five != 0 && ten != 0) {
                    five --;
                    ten --;
                } else if (five >= 3) {
                    five -=3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
