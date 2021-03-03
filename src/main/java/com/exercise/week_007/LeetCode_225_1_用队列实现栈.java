package main.java.com.exercise.week_007;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/3/3 17:36
 * @Version 1.0
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class LeetCode_225_1_用队列实现栈 {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    /** Initialize your data structure here. */
    public LeetCode_225_1_用队列实现栈() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
