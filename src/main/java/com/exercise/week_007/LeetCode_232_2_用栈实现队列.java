package main.java.com.exercise.week_007;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/3/3 16:42
 * @Version 1.0
 */
public class LeetCode_232_2_用栈实现队列 {
    private Stack<Integer> instack;
    private Stack<Integer> outStack;
    /** Initialize your data structure here. */
    public LeetCode_232_2_用栈实现队列() {
        instack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        instack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) {
            while (!instack.isEmpty()) {
                outStack.push(instack.pop());
            }
        }
        return outStack.pop();

    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) {
            while (!instack.isEmpty()) {
                outStack.push(instack.pop());
            }
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return instack.isEmpty() && outStack.empty();
    }
}
