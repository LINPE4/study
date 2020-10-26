/**
 * Project: arithmeticdemo
 * File created at 2020/10/25 15:07
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *  剑指 Offer 35. 复杂链表的复制   https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 * @author linlipeng
 * @version 1.0
 * @type CopyRandomList
 * @date 2020/10/25 15:07
 */
public class CopyRandomList {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val);
            map.put(curr, newNode);
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }
}
