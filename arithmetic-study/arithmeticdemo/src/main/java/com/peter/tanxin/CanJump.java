/**
 * Project: arithmeticdemo
 * File created at 2020/11/7 16:03
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tanxin;

/**
 * 功能描述
 *   55. 跳跃游戏  https://leetcode-cn.com/problems/jump-game/
 * @author linlipeng
 * @version 1.0
 * @type CanJump
 * @date 2020/11/7 16:03
 */
public class CanJump {

    public boolean canJump(int[] nums) {
        //maxStep表示能到达的距离
        int maxStep = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //如果跳不到位置i，直接返回false
            if (i > maxStep)
                return false;
            //如果能跳到位置i，就更新所能跳的最大距离
            maxStep = Math.max(maxStep, i + nums[i]);
            //如果能跳到最后的位置，说明能够成功，直接终止循环
            if (maxStep >= length)
                return true;
        }
        return true;
    }

}
