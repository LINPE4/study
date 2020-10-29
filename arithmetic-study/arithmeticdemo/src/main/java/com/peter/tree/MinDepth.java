/**
 * Project: arithmeticdemo
 * File created at 2020/10/29 16:53
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

/**
 * 功能描述
 *  111. 二叉树的最小深度     https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * @author linlipeng
 * @version 1.0
 * @type MinDepth
 * @date 2020/10/29 16:53
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return left == 0 || right == 0 ? left + right + 1 : Math.min(left, right) + 1;
    }

}
