package main.java.com.exercise.week_051;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/13 20:31
 */
public class LeetCode_121_2_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // d[i][j]分别代表第i天，拥有j个股票的最大收益
        int[][] dp = new int[n][2];
        // 初始化数据
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }
}
