/**
 * Project: arithmeticdemo
 * File created at 2020/11/7 14:03
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 功能描述
 *   515. 在每个树行中找最大值   https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 * @author linlipeng
 * @version 1.0
 * @type LargestValues
 * @date 2020/11/7 14:03
 */
public class LargestValues {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            Long max = Long.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (max != Long.MIN_VALUE) {
                result.add(max.intValue());
            }
        }
        return result;
    }
}
