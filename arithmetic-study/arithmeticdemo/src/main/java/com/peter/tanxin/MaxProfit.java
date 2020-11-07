/**
 * Project: arithmeticdemo
 * File created at 2020/11/7 15:32
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tanxin;

/**
 * 功能描述
 *   122. 买卖股票的最佳时机 II    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * @author linlipeng
 * @version 1.0
 * @type MaxProfit
 * @date 2020/11/7 15:32
 */
public class MaxProfit {

    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i-1];
            }
        }
        return sum;
    }
}
