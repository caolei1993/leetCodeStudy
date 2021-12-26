package main.java.com.exercise.week_044;

import java.util.PriorityQueue;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/26 9:20 下午
 */
public class LeetCode_1705_1_吃苹果的最大数目 {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length, time = 0, ans = 0;

        while (time < n || !queue.isEmpty()) {
            // 如果没到最大天数，将产出的苹果添加到队列
            if (time < n && apples[time] > 0) {
                queue.offer(new int[]{time + days[time] - 1, apples[time]});
            }
            // 将过期腐烂的苹果去除
            while (!queue.isEmpty() && queue.peek()[0] < time) {
                queue.poll();
            }
            // 吃苹果
            if (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int count = --cur[1];
                if (cur[0] > time && count > 0) {
                    queue.offer(cur);
                }
                ans++;
            }
            time++;
        }
        return ans;
    }
}
