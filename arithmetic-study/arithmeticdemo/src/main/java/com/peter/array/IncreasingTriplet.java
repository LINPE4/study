/**
 * Project: arithmeticdemo
 * File created at 2020/11/17 19:28
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.array;

/**
 * 功能描述
 *   334. 递增的三元子序列  https://leetcode-cn.com/problems/increasing-triplet-subsequence/
 * @author linlipeng
 * @version 1.0
 * @type IncreasingTriplet
 * @date 2020/11/17 19:28
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        int len=nums.length;
        if(nums.length<3) return false;
        int min=Integer.MAX_VALUE; //最小值
        int second=Integer.MAX_VALUE;//第二元素最小的递增对的第二元素
        for(int num:nums){
            if(num<=min){//num比min小或相等，肯定不存在递增三元素。由于不存在以num结尾的递增对，故只需更新min
                min=num;
            }else if(num<=second){//num比second小或相对，肯定不存在递增三元素。由于存在以num结尾的递增对且num不大于second，因此可以更新second
                second=num;
            }else {//num比second大，那就存在递增三元素，因为second是一个递增对的第二元素，加上num后就是递增三元素了
                return true;
            }
        }
        return false;

    }
}
