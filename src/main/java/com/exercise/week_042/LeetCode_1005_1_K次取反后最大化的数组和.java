package main.java.com.exercise.week_042;

import java.util.PriorityQueue;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/3 6:00 下午
 */
public class LeetCode_1005_1_K次取反后最大化的数组和 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int n : nums) {
            queue.offer(n);
        }

        while (k-- > 0) {
            int v = queue.poll();
            queue.offer(-v);
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ans += queue.poll();
        }
        return ans;
    }
}
