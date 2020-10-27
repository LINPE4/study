/**
 * Project: arithmeticdemo
 * File created at 2020/10/27 14:46
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

/**
 * 功能描述
 *   98. 验证二叉搜索树  https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author linlipeng
 * @version 1.0
 * @type IsValidBST
 * @date 2020/10/27 14:46
 */
public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        boolean result = valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return result;
    }

    private boolean valid(TreeNode root, long  min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return valid(root.left, min, root.val) && valid(root.right, root.val, max);
    }
}
