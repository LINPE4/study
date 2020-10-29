/**
 * Project: arithmeticdemo
 * File created at 2020/10/29 15:16
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

/**
 * 功能描述
 *      104. 二叉树的最大深度  https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @author linlipeng
 * @version 1.0
 * @type MaxDepth
 * @date 2020/10/29 15:16
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = getDepth(root, 1);
        return result;
    }

    private int getDepth(TreeNode root, int n) {
        if (root.left == null && root.right == null) {
            return n;
        }

        int leftN = 0;
        int rightN = 0;
        if (root.left != null) {
            leftN = getDepth(root.left, n + 1);
        }
        if (root.right != null) {
            rightN = getDepth(root.right, n+ 1);
        }
        return Math.max(leftN, rightN);
    }
}
