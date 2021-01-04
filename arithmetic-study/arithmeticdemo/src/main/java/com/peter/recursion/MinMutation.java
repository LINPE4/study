/**
 * Project: arithmeticdemo
 * File created at 2020/11/5 19:15
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.recursion;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 功能描述
 *    433. 最小基因变化  https://leetcode-cn.com/problems/minimum-genetic-mutation/
 * @author linlipeng
 * @version 1.0
 * @type MinMutation
 * @date 2020/11/5 19:15
 */
public class MinMutation {
    int minStepCount = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        dfs(new HashSet<>(), 0, start, end, bank);
        return minStepCount == Integer.MAX_VALUE ? -1 : minStepCount;
    }

    private void dfs(HashSet<String> set, Integer step,String start, String end, String[] bank) {
        if (step >= minStepCount) {
            return;
        }
        // 如果相等，则比较步数，取最小的步数
        if (start.equals(end)) {
            minStepCount = Math.min(step, minStepCount);
            return;
        }
        // 遍历基因库
        for (String str : bank) {
            int diff = 0;
            for (int i = 0; i < str.toCharArray().length; i++) {
                if (start.charAt(i) != str.charAt(i)) {
                    // 如果本基因存在一个以上字符不相等，则选择下一个基因
                    if (++diff > 1) {
                        break;
                    }
                }
            }
            if (diff == 1 && !set.contains(str)) {
                // 匹配到基因库，进行下一个匹配
                set.add(str);
                dfs(set, step + 1, str, end, bank);
                // 回溯，丢弃此基因库匹配
                set.remove(str);
            }
        }
    }

}
