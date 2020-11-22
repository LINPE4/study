/**
 * Project: arithmeticdemo
 * File created at 2020/11/22 13:42
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *
 *      148. 排序链表   https://leetcode-cn.com/problems/sort-list/
 *     对链表自顶向下归并排序的过程如下。
 *          找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动 22 步，
 *          慢指针每次移动 11 步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
 *          对两个子链表分别排序。
 *
 *      将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
 *      上述过程可以通过递归实现。递归的终止条件是链表的节点个数小于或等于 11，即当链表为空或者链表只包含 11 个节点时，不需要对链表进行拆分和排序。
 *
 * @author linlipeng
 * @version 1.0
 * @type SortList
 * @date 2020/11/22 13:42
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode leftListNode = sortList(head, mid);
        ListNode rightListNode = sortList(mid, tail);
        return merge(leftListNode, rightListNode);
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2!= null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

}
