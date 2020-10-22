/**
 * Project: arithmeticdemo
 * File created at 2020/10/22 11:25
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *     142. 环形链表 II         https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * @author linlipeng
 * @version 1.0
 * @type DetectCycle
 * @date 2020/10/22 11:25
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast==slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
