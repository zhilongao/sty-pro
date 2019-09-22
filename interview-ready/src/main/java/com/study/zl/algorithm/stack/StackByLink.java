package com.study.zl.algorithm.stack;

/**
 * @Author long
 * @Date 2019/8/11 15:57
 * 基于链表实现栈
 */
public class StackByLink {
    private static ListNode stackTop;
    private static ListNode stackBottom;


    public static void push(ListNode node) {
        node.next = stackTop;
        stackTop = node;
    }

    public static int pop() {
        int value = stackTop.value;
        stackTop = stackTop.next;
        return value;
    }

    public static void reverse() {
        ListNode oldNode = stackTop;
        while (oldNode != null) {
            System.out.print(oldNode.value + "\t");
            oldNode = oldNode.next;
        }
        System.out.println();
    }


    static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        ListNode stackHead = new ListNode(1);
        push(new ListNode(2));
        push(new ListNode(3));
        push(new ListNode(4));
        push(new ListNode(5));
        push(new ListNode(6));
        push(new ListNode(7));
        reverse();
        pop();
        pop();
        pop();
        pop();
        reverse();
    }
}


