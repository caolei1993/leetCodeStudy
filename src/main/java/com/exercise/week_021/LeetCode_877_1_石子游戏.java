package main.java.com.exercise.week_021;

/**
 * @Author cl
 * @Date 2021/6/16 17:29
 * @Version 1.0
 */
public class LeetCode_877_1_石子游戏 {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // 定义dp数组，f[l][r]表示在[l,r]区间里，两个玩家获取石头总和的最大差值
        int[][] f = new int[n + 2][n + 2];
        // 遍历区间长度
        for (int len = 1; len <= n; len++) {
            // 遍历左端点
            for (int l = 1; l + len - 1 <= n; l++) {
                // 计算右端点
                int r = l + len - 1;
                // 选择左端点
                int a = piles[l - 1] - f[l + 1][r];
                // 选择右端点
                int b = piles[r - 1] - f[l][r - 1];
                f[l][r] = Math.max(a, b);
            }
        }
        return f[1][n] > 0;
    }
}
