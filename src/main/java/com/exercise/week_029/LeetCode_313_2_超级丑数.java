package main.java.com.exercise.week_029;

import java.util.PriorityQueue;

/**
 * @Author cl
 * @Date 2021/8/10 21:41
 * @Version 1.0
 */
public class LeetCode_313_2_超级丑数 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a , b) -> a[0] - b[0]);
        for (int i = 0; i < m; i++) {
            queue.add(new int[]{primes[i], i, 0});
        }
        int[] ans = new int[n];
        ans[0] = 1;
        for (int j = 1; j < n; ) {
            int[] p = queue.poll();
            int val = p[0], i = p[1], idx = p[2];
            if (val != ans[j - 1]) {
                ans[j++] = val;
            }
            queue.add(new int[]{ans[idx + 1] * primes[i], i, idx + 1});
        }
        return ans[n - 1];
    }
}
