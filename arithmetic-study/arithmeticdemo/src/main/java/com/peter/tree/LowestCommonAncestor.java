/**
 * Project: arithmeticdemo
 * File created at 2020/10/30 13:56
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

/**
 * 功能描述
 * 236. 二叉树的最近公共祖先   https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @author linlipeng
 * @version 1.0
 * @type LowestCommonAncestor
 * @date 2020/10/30 13:56
 */
public class LowestCommonAncestor {
    TreeNode node = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return node;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((lson || rson) && (root.val == p.val || root.val == q.val))) {
            node = root;
        }

        return lson || rson || (root.val == p.val || root.val == q.val);
    }

}
