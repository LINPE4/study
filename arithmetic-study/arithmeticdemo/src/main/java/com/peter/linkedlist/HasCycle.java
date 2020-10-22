/**
 * Project: arithmeticdemo
 * File created at 2020/10/22 11:04
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述
 *  141. 环形链表  https://leetcode-cn.com/problems/linked-list-cycle/
 * @author linlipeng
 * @version 1.0
 * @type HasCycle
 * @date 2020/10/22 11:04
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head.next)) {
                return true;
            }
            head = head.next;
        }

        return false;
    }
}
