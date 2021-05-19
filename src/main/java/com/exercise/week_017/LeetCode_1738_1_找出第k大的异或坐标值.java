package main.java.com.exercise.week_017;

import java.util.PriorityQueue;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/19 21:10
 */
public class LeetCode_1738_1_找出第k大的异或坐标值 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[m + 1][n + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> a-b);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n ; j++) {
                f[i][j] = f[i - 1][j] ^ f[i][j - 1] ^ f[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                if (queue.size() < k) {
                    queue.add(f[i][j]);
                } else {
                    if (f[i][j] > queue.peek()) {
                        queue.poll();
                        queue.add(f[i][j]);
                    }
                }
            }
        }
        return queue.peek();
    }
}
