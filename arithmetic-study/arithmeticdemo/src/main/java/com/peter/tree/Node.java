/**
 * Project: arithmeticdemo
 * File created at 2020/10/27 9:53
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.tree;

import java.util.List;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type Node
 * @date 2020/10/27 9:53
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
