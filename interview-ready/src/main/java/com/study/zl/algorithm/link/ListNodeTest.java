package com.study.zl.algorithm.link;

/**
 * @Author long
 * @Date 2019/8/11 9:50
 */
public class ListNodeTest {

    public static void main(String[] args) {
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(3);
        ListNode node14 = new ListNode(4);
        ListNode node15 = new ListNode(5);
        node11.next = node12;
        node12.next = node13;
        //node13.next = node14;
        //node14.next = node15;

        ListNode node21  = new ListNode(1);
        ListNode node22  = new ListNode(8);
        ListNode node23  = new ListNode(3);
        ListNode node24  = new ListNode(6);
        ListNode node25  = new ListNode(5);
        ListNode node26  = new ListNode(4);
        ListNode node27  = new ListNode(7);
        ListNode node28  = new ListNode(2);
        ListNode node29  = new ListNode(9);
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;
        node24.next = node25;
        node25.next = node26;
        node26.next = node27;
        node27.next = node28;
        node28.next = node29;

        BaseOperate.traversal(node21);
        ListNode node = CombatOperate.reOrder(node21);
        BaseOperate.traversal(node);

    }
}
