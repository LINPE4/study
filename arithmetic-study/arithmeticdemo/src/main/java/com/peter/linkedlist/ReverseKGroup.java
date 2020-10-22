/**
 * Project: arithmeticdemo
 * File created at 2020/10/22 13:39
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *   25. K 个一组翻转链表  https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * @author linlipeng
 * @version 1.0
 * @type ReverseKGroup
 * @date 2020/10/22 13:39
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode start) {
        ListNode newListNode = null;
        ListNode curr = start;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = newListNode;
            newListNode = curr;
            curr = next;
        }
        return newListNode;
    }
}
