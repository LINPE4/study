/**
 * Project: arithmeticdemo
 * File created at 2020/11/24 11:05
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

/**
 * 功能描述
 *    234. 回文链表 https://leetcode-cn.com/problems/palindrome-linked-list/
 * @author linlipeng
 * @version 1.0
 * @type IsPalindrome
 * @date 2020/11/24 11:05
 */
public class IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表的长度是奇数个
        if (fast != null) {
            slow = slow.next;
        }
        //反转后半部分链表
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            //然后比较，判断节点值是否相等
            if (fast.val != slow.val)
                return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    //反转链表
    public ListNode reverse(ListNode head) {
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

}
