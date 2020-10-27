/**
 * Project: arithmeticdemo
 * File created at 2020/10/27 9:54
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述
 *    590. N叉树的后序遍历   https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * @author linlipeng
 * @version 1.0
 * @type Postorder
 * @date 2020/10/27 9:54
 */
public class PostOrder {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        getAll(root, result);
        return result;
    }

    private void getAll(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        for (Node node: root.children) {
            getAll(node, result);
        }
        result.add(root.val);
    }


}
