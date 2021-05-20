/**
 * Project: arithmeticdemo
 * File created at 2021/5/20 11:01
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.peter.linkedlist;

import java.util.HashMap;

/**
 * 功能描述
 *      LRU cache: 淘汰最少使用的v
 * @author linlipeng
 * @version 1.0
 * @type LRUCache
 * @date 2021/5/20 11:01
 */
public class LRUCache<K,V> {

    private int capactity;
    private HashMap<K, Node> map;
    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCache() {

    }

    public LRUCache(int capactity) {
        this.capactity = capactity;
        map = new HashMap<>(capactity);
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
    }

    public void put(K k, V v) {
        // 根据k从hashmap中获取
        Node<K, V> node = map.get(k);
        if (node == null) {
            // 不存在此值
            if (map.size() >= capactity) {
                // 已经达到cache大小了
                // 移除map key
                map.remove(tail.prev.key);
                // 移除tail的上一个node
                removeTailNode();
            }
            Node<K, V> newNode = new Node<>(k, v);
            // 新增map
            map.put(k, newNode);
            // 将node移到head 的next
            addToHead(newNode);
        } else {
            // 更新node的值
            node.value = v;
            // 将node移到head的next
            moveNodeToHead(node);
        }
    }

    public V get(K k) {
        Node<K, V> node = map.get(k);
        // 从map中获取node
        if (node == null) {
            // node为空，直接返回null
            return null;
        }
        // node 不为空， 将node移到head的next
        moveNodeToHead(node);
        // 返回node的value
        return node.value;
    }


    private void addToHead(Node<K,V> newNode) {
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
    }

    private void removeTailNode() {
        removeNode(tail.prev);
    }

    private void moveNodeToHead(Node<K,V> node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev, next;
        public Node(){}
        public Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }
}
