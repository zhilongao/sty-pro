package com.study.zl.algorithm.link;

/**
 * @Author long
 * @Date 2019/8/11 9:24
 */
public class BaseOperate {
    /**
     * 插入头结点
     * @param head
     * @param node
     */
    public static void insertHead(ListNode head, ListNode node) {
        node.next = head;
        head = node;
    }

    /**
     * 插入尾节点
     * @param tail
     * @param node
     */
    public static void insertTail(ListNode tail, ListNode node) {
        tail.next = node;
        tail = node;
    }

    /**
     * 删除某个节点
     * @param node
     */
    public static void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.value = next.value;
        node.next = next.next;
        next.next = null;
    }

    /**
     * 遍历单链表
     * @param head
     */
    public static void traversal(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "\t");
            head = head.next;
        }
        System.out.println();
    }



}
