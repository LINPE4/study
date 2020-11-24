/**
 * Project: arithmeticdemo
 * File created at 2020/11/24 14:56
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *   237. 删除链表中的节点   https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author linlipeng
 * @version 1.0
 * @type DeleteNode
 * @date 2020/11/24 14:56
 */
public class DeleteNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
