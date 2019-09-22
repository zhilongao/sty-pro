package com.study.zl.algorithm.link;

/**
 * @Author long
 * @Date 2019/8/11 9:39
 */
public class CombatOperate {

    /**
     * 获取单链表的中间节点(若为奇数个,返回中间的->若为偶数个,返回第一个)
     * @param head
     * @return
     */
    public static ListNode getMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }







    public static void reverse1(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        while (head != null && (next = head.next) != null) {
            head.next = prev;
            prev = head;
            head = next;
        }
        head.next = prev;
    }




    /**
     * 反转单链表
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        while (head != null && (next = head.next) != null) {
            head.next = prev;
            prev = head;
            head = next;
        }
        head.next = prev;
        return head;
    }


    /**
     * 合并两个有序链表
     * @param node1
     * @param node2
     * @return
     */
    public static ListNode mergeOrdered(ListNode node1, ListNode node2) {
        // 若是有一个链表为空,直接返回
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;
        while (node1 != null && node2 != null) {
            if (node1.value <= node2.value) {
                pre.next = node1;
                node1 = node1.next;
            } else {
                pre.next = node2;
                node2 = node2.next;
            }
            // 每次需要更新pre
            pre = pre.next;
        }
        pre.next = node1 == null ? node2 : node1;
        return preHead.next;
    }

    /**
     * 奇数位升序,偶数位降序的链表进行排序
     * 比如：1 8 3 6 5 4 7 2 9，最后输出1 2 3 4 5 6 7 8 9。
     * @param node
     * @return
     */
    public static ListNode reOrder(ListNode node) {
        // 拆分成为两个俩表
        ListNode head1 = null;
        ListNode head2 = null;
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        int index = 1;
        while (node != null) {
            if (index % 2 != 0) {
                if (cur1 != null) {
                    cur1.next = node;
                    cur1 = cur1.next;
                } else {
                    cur1 = node;
                    head1 = cur1;
                }
            } else {
                if (cur2 != null) {
                    cur2.next = node;
                    cur2 = cur2.next;
                } else {
                    cur2 = node;
                    head2 = cur2;
                }
            }
            node = node.next;
            index = index + 1;
        }
        cur1.next = null;
        cur2.next = null;
        // 将偶数位链表进行反转
        ListNode reverse = reverse(head2);
        // 将两个链表排序
        return mergeOrdered(head1, reverse);
    }
}
