package com.study.zl.algorithm.stack;

import java.util.LinkedList;

/**
 * @Author long
 * @Date 2019/8/19 11:30
 */
public class StackToLink {

    public static void main(String[] args) {

    }
}

/**
 * 使用栈来实现队列
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 */
class MyQueue {

    static class Node{
        int value;
        Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node head;
    Node tail;

    public MyQueue() {

    }

    public void push(int x) {
        Node node = new Node(x, null);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
        }
    }

    public int pop() {
        if (head == null) {
            return 0;
        }
        Node oldHead = head;
        int value = oldHead.value;
        head = oldHead.next;
        return value;
    }

    public int peek() {
        if (head == null) {
            return 0;
        }
        return head.value;
    }

    public boolean empty() {
        return head == null;
    }
}
