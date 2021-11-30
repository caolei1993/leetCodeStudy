package main.java.com.exercise.week_027;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/8/2 9:46
 * @Version 1.0
 */
public class LeetCode_1337_1_矩阵中战斗力最弱的k行 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] counts = new int[m][2];
        for (int i = 0; i < m; i++) {
            int[] ma = mat[i];
            int sum = 0;
            for (int v : ma) {
                if (v == 1) {
                    sum++;
                } else {
                    break;
                }
            }
            counts[i] = new int[]{sum, i};
        }
        Arrays.sort(counts, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = counts[i][1];
        }
        return ans;
    }
}
