package main.java.com.exercise.week_042;

import java.util.PriorityQueue;

public class LeetCode_786_2_第K个最小的素数分数 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 优先队列从小到大排列，队头为最小元素
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
           double t1 = arr[a[0]] * 1.0 / arr[a[1]];
           double t2 = arr[b[0]] * 1.0 / arr[b[1]];
           return Double.compare(t1, t2);
        });
        int n = arr.length;
        // 将没有有序队列的头存入优先队列中
        for (int i = 1; i < n; i++) {
            queue.offer(new int[]{0, i});
        }
        // 将前k-1个小值都poll掉
        while (k-- > 1) {
            int[] v = queue.poll();
            int i = v[0], j = v[1];
            if (i + 1 < j) {
                queue.offer(new int[]{i + 1, j});
            }
        }
        // 取出第K个小值
        int[] val = queue.poll();
        return new int[]{arr[val[0]], arr[val[1]]};
    }
}
