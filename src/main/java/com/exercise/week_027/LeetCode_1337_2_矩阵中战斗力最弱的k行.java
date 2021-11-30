package main.java.com.exercise.week_027;

import java.util.PriorityQueue;

/**
 * @Author cl
 * @Date 2021/8/2 10:51
 * @Version 1.0
 */
public class LeetCode_1337_2_矩阵中战斗力最弱的k行 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < m; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (mat[i][mid] < 1) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            int cur = mat[i][l] >= 1 ? r + 1 : r;
            queue.add(new int[]{cur, i});
        }
        int[] ans = new int[k];
        int index = 0;
        while (k-- > 0) {
            ans[index] = queue.poll()[1];
            index++;
        }
        return ans;
    }
}
