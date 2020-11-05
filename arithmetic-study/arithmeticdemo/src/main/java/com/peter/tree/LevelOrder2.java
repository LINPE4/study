/**
 * Project: arithmeticdemo
 * File created at 2020/11/5 18:56
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 功能描述
 *     102. 二叉树的层序遍历  https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description
 * @author linlipeng
 * @version 1.0
 * @type LevelOrder2
 * @date 2020/11/5 18:56
 */
public class LevelOrder2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        return result;
    }
}
