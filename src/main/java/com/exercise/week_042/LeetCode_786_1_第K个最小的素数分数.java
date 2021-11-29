package main.java.com.exercise.week_042;

import java.util.PriorityQueue;

public class LeetCode_786_1_第K个最小的素数分数 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 优先队列从大到小排列，队头为最大元素
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[0] * 1.0 / b[1], a[0] * 1.0 / a[1]));
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 当前遍历的值
                double t = arr[i] * 1.0 / arr[j];
                if (queue.size() > 0 && queue.peek()[0] * 1.0 / queue.peek()[1] > t) {
                    if (queue.size() == k) {
                        queue.poll();
                    }
                    queue.offer(new int[]{arr[i], arr[j]});
                }
            }
        }
        return queue.poll();
    }
}
