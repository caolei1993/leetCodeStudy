package main.java.com.exercise.week_049;

import java.util.PriorityQueue;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/7 10:10 上午
 */
public class LeetCode_1405_1_最长快乐字符串 {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y)-> y[1] - x[1]);
        if (a != 0) {
            queue.offer(new int[]{0, a});
        }
        if (b != 0) {
            queue.offer(new int[]{1, b});
        }
        if (c != 0) {
            queue.offer(new int[]{2, c});
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 1) - 'a' == cur[0] && sb.charAt(len - 2) - 'a' == cur[0]) {
                if (queue.isEmpty()) {
                    break;
                }
                int[] next = queue.poll();
                sb.append((char)(next[0] + 'a'));
                next[1]--;
                if (next[1] > 0) {
                    queue.offer(next);
                }
                queue.offer(cur);
            } else {
                sb.append((char)(cur[0] + 'a'));
                cur[1]--;
                if (cur[1] > 0) {
                    queue.offer(cur);
                }
            }
        }
        return sb.toString();
    }
}
