package main.java.com.exercise.week_020;

/**
 * @Author cl
 * @Date 2021/6/10 20:25
 * @Version 1.0
 */
public class LeetCode_518_1_零钱兑换II {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // f[i][j]代表利用前i种硬币凑成金额j总共的方案数
        int[][] f = new int[n + 1][amount + 1];
        // 初始化没有硬币，凑成金额0，方案数为1
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int value = coins[i -1];
            for (int j = 0; j <= amount; j++) {
                // 不选第i-1种硬币
                f[i][j] = f[i - 1][j];
                // 选第i-1种硬币
                for (int k = 1; k * value <= j; k++) {
                    f[i][j] += f[i - 1][j - k * value];
                }
            }
        }
        return f[n][amount];
    }
}
