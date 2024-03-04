package main.java.com.exercise.week_061;

import java.util.*;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/29 19:56
 */
public class LeetCode_155_2_最小栈 {
    Deque<Integer> deque;
    Deque<Integer> minQueue;
    public LeetCode_155_2_最小栈() {
        deque = new LinkedList<>();
        minQueue = new LinkedList<>();
    }

    public void push(int val) {
        deque.push(val);
        minQueue.push(minQueue.peek() == null ? val : Math.min(minQueue.peek(), val));

    }

    public void pop() {
        deque.pop();
        minQueue.pop();
    }

    public int top() {
        return deque.peek();
    }

    public int getMin() {
        return minQueue.peek();
    }
}
