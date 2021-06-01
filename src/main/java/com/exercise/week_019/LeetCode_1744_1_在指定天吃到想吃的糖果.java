package main.java.com.exercise.week_019;

/**
 * @Author cl
 * @Date 2021/6/1 21:22
 * @Version 1.0
 */
public class LeetCode_1744_1_在指定天吃到想吃的糖果 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length, m = queries.length;
        boolean[] ans = new boolean[m];
        // 求取前缀和，来确认吃第i类糖果前共需要吃多少个糖果
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + candiesCount[i - 1];
        }
        for (int i = 0; i < m; i++) {
            int type = queries[i][0], day = queries[i][1] + 1, daily = queries[i][2];
            long min = sum[type] / daily + 1, max = sum[type + 1];
            ans[i] = day >= min && day <= max;
        }
        return ans;
    }
}
