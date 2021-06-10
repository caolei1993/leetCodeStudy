package main.java.com.exercise.week_020;

/**
 * @Author cl
 * @Date 2021/6/10 21:41
 * @Version 1.0
 */
public class LeetCode_518_2_零钱兑换II {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // f[j]代表凑成金额j总共的方案数
        int[] f = new int[amount + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int value = coins[i -1];
            // 金额从value上涨的过程中其实已经包含了选择【1,amount/coins[i-1]】个该种硬币的过程
            for (int j = value; j <= amount; j++) {
                f[j] += f[j - value];
            }
        }
        return f[amount];
    }
}
