/**
 * Project: arithmeticdemo
 * File created at 2020/10/25 14:55
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type ListNode
 * @date 2020/10/25 14:55
 */
public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
      }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
