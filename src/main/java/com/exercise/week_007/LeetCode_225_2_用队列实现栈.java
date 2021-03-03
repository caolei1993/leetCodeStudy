package main.java.com.exercise.week_007;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/3/3 18:00
 * @Version 1.0
 */
public class LeetCode_225_2_用队列实现栈 {
    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public LeetCode_225_2_用队列实现栈() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        int lengh = queue.size();
        queue.offer(x);
        for (int i = 0; i < lengh; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
