/**
 * Project: arithmeticdemo
 * File created at 2020/10/30 10:42
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 功能描述
 *    297. 二叉树的序列化与反序列化 https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * @author linlipeng
 * @version 1.0
 * @type Codec
 * @date 2020/10/30 10:42
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String result = doSerialize(root, "");
        return result;
    }

    private String doSerialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += root.val + ",";
            str = doSerialize(root.left, str);
            str = doSerialize(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return doDeserialize(data_list);

    }

    private TreeNode doDeserialize(List<String> list) {
        if (list.get(0).equals("None")) {
            list.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        node.left = doDeserialize(list);
        node.right = doDeserialize(list);
        return node;
    }

}
