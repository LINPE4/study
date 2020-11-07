/**
 * Project: arithmeticdemo
 * File created at 2020/11/7 15:49
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tanxin;

import java.util.Arrays;

/**
 * 功能描述
 *  455. 分发饼干   https://leetcode-cn.com/problems/assign-cookies/
 * @author linlipeng
 * @version 1.0
 * @type FindContentChildren
 * @date 2020/11/7 15:49
 */
public class FindContentChildren {

    public int findContentChildren(int[] g, int[] s) {
        if (g == null|| s == null ) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0, sIndex = 0;
        while (gIndex < g.length && sIndex < s.length) {
            if (g[gIndex] <= s[sIndex]) {
                gIndex ++;
            }
            sIndex ++;
        }
        return gIndex;
    }
}
