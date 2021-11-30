package main.java.com.exercise.week_041;

/**
 * @Author cl
 * @Date 2021/11/12 17:06
 * @Version 1.0
 */
public class LeetCode_375_1_猜数字大小II {
    public int getMoneyAmount(int n) {
        // f[i][j]代表在区间[i,j]中猜数的最小花费
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1 ; i--) {
            for (int j = i + 1; j <= n; j++) {
                int val = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cur = Math.max(f[i][k - 1], f[k+1][j]) + k;
                    val = Math.min(val, cur);
                }
                f[i][j] = val;
            }
        }
        return f[1][n];
    }
}
