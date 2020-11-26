/**
 * Project: arithmeticdemo
 * File created at 2020/11/26 11:08
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 功能描述
 *    347. 前 K 个高频元素   https://leetcode-cn.com/problems/top-k-frequent-elements/
 * @author linlipeng
 * @version 1.0
 * @type TopKFrequent
 * @date 2020/11/26 11:08
 */
public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        int[] res = new int[pq.size()];
        int i = 0;
        while (!pq.isEmpty()) {
            res[i] = (pq.remove());
            i ++;
        }
        return res;
    }
}
