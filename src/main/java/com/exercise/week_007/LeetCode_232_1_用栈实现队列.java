package main.java.com.exercise.week_007;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/3/2 18:07
 * @Version 1.0
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
public class LeetCode_232_1_用栈实现队列 {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public LeetCode_232_1_用栈实现队列() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(x);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
