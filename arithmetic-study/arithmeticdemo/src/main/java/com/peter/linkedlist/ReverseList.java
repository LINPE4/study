/**
 * Project: arithmeticdemo
 * File created at 2020/10/21 14:42
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *     206. 反转链表     https://leetcode-cn.com/problems/reverse-linked-list/
 *     思路： 举例  原链表为 1 -》 2-》 3  反转结果为 3 》 2》 1
 *             过程：先将 1.nextTemp = 2》3，  1 》 null，
 *                        2. nextTemp = 3， 2》 1
 *                        3  nextTemp = null， 3》2》1
 * @author linlipeng
 * @version 1.0
 * @type ReverseList
 * @date 2020/10/21 14:42
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode newListNode = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = newListNode;
            newListNode = curr;
            curr = nextTemp;
        }
        return newListNode;
    }

    public static void main(String[] args) {
        ReverseList test = new ReverseList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode result = test.reverseList(head);
        System.out.println(result);
    }
}

