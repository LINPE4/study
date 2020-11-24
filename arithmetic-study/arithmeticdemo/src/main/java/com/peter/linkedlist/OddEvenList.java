/**
 * Project: arithmeticdemo
 * File created at 2020/11/24 15:10
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *   328. 奇偶链表  https://leetcode-cn.com/problems/odd-even-linked-list/
 * @author linlipeng
 * @version 1.0
 * @type OddEvenList
 * @date 2020/11/24 15:10
 */
public class OddEvenList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;

    }
}
