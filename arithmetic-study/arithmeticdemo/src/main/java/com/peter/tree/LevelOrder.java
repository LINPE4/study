/**
 * Project: arithmeticdemo
 * File created at 2020/10/27 10:42
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 功能描述
 *     429. N叉树的层序遍历   https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * @author linlipeng
 * @version 1.0
 * @type LevelOrder
 * @date 2020/10/27 10:42
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                list.add(node.val);
                for (Node c: node.children) {
                    if (c != null) {
                        queue.add(c);
                    }
                }
            }
            result.add(list);
        }
        return result;

    }
}
