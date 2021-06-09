package main.java.com.exercise.week_020;

/**
 * @Author cl
 * @Date 2021/6/9 21:23
 * @Version 1.0
 */
public class LeetCode_879_1_盈利计划 {
    int v = (int) 1e9 + 7;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        // f[i][j][k]代表前i个任务，使用人数不超过j，至少获得k的利润的方案数
        int[][][] f = new int[m + 1][n + 1][minProfit + 1];
        // 初始化动态规则的值，没有任务，没有利润，无论多少人都是一个方案
        for (int i = 0; i <= n ; i++) {
            f[0][i][0] = 1;
        }
        // 遍历任务
        for (int i = 1; i <= m; i++) {
            int g = group[i - 1], p = profit[i - 1];
            // 遍历人数
            for (int j = 0; j <= n; j++) {
                // 遍历利润
                for (int k = 0; k <= minProfit ; k++) {
                    // 不选第i - 1个任务
                    f[i][j][k] = f[i - 1][j][k];
                    // 选第i-1个任务
                    // 计算差的利润值(当前任务获得的利润大于等于k时，之前的任务允许获取0利润)
                    int value = Math.max(k - p, 0);
                    if (j >= g) {
                        f[i][j][k] += f[i - 1][j - g][value];
                    }
                    if (f[i][j][k] > v) {
                        f[i][j][k] -= v;
                    }
                }
            }
        }
        return f[m][n][minProfit];
    }
}
