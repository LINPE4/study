/**
 * Project: arithmeticdemo
 * File created at 2020/11/22 14:54
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述
 *   160. 相交链表
 *   思路， 要求出两个相交点，假如都走同样的距离（即a路= a路+b路，b路= a路+b路）， 则最后相交的点肯定会相遇
 * @author linlipeng
 * @version 1.0
 * @type GetIntersectionNode
 * @date 2020/11/22 14:54
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

}
