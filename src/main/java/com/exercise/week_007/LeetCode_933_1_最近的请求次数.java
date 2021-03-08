package main.java.com.exercise.week_007;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/3/4 13:43
 * @Version 1.0
 * https://leetcode-cn.com/problems/number-of-recent-calls/
 */
public class LeetCode_933_1_最近的请求次数 {
    Queue<Integer> queue;
    public LeetCode_933_1_最近的请求次数() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        int lower = t - 3000;
        queue.offer(t);
        while (queue.peek() < lower) {
            queue.poll();
        }
        return queue.size();
    }
}
