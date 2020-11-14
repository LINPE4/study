/**
 * Project: arithmeticdemo
 * File created at 2020/11/12 14:14
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.dp;

/**
 * 功能描述
 *      152. 乘积最大子数组  https://leetcode-cn.com/problems/maximum-product-subarray/
 *    遍历数组时计算当前最大值，不断更新
 *  令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
 * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
 * 当负数出现时则imax与imin进行交换再进行下一步计算
 *
 * @author linlipeng
 * @version 1.0
 * @type MaxProduct
 * @date 2020/11/12 14:14
 */
public class MaxProduct {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

}