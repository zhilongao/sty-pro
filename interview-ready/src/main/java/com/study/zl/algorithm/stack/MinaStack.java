package com.study.zl.algorithm.stack;

import java.util.Stack;

/**
 * @Author long
 * @Date 2019/8/18 17:20
 */
public class MinaStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(-3);
        minStack.push(0);
        minStack.getMin();   // --> 返回 -3.
        minStack.pop();
        minStack.top();      //--> 返回 0.
        minStack.getMin();
    }

}

/**
 * 获取占中最小值
 */
class MinStack {

    Stack<Integer> data;
    Stack<Integer> helper;

    public MinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    public void push(int x) {
        data.add(x);
        if (helper.isEmpty() || helper.peek() >= x) {
            helper.add(x);
        } else {
            helper.add(helper.peek());
        }
    }

    public void pop() {
        if (!data.isEmpty()) {
            data.pop();
            helper.pop();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return helper.peek();
    }
}



