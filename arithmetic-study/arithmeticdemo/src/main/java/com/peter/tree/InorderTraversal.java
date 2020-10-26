/**
 * Project: arithmeticdemo
 * File created at 2020/10/26 18:49
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能描述
 *   94. 二叉树的中序遍历   https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * @author linlipeng
 * @version 1.0
 * @type InorderTraversal
 * @date 2020/10/26 18:49
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            getNode(root, result);
        }
        return result;
    }

    private void getNode(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            getNode(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            getNode(root.right, result);
        }
    }
}
