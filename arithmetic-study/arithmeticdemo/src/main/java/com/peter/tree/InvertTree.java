/**
 * Project: arithmeticdemo
 * File created at 2020/10/27 14:18
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

/**
 * 功能描述
 *   226. 翻转二叉树  https://leetcode-cn.com/problems/invert-binary-tree/
 * @author linlipeng
 * @version 1.0
 * @type InvertTree
 * @date 2020/10/27 14:18
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        handle(root);
        return root;
    }

    private void handle(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode temp = left;
        root.left = root.right;
        root.right = temp;
        handle(root.left);
        handle(root.right);
    }
}
