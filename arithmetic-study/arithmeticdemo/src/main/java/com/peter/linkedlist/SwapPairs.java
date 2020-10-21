/**
 * Project: arithmeticdemo
 * File created at 2020/10/21 16:48
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 * 24. 两两交换链表中的节点    https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * @author linlipeng
 * @version 1.0
 * @type SwapPairs
 * @date 2020/10/21 16:48
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }
}

