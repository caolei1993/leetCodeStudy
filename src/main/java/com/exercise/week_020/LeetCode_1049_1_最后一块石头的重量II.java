package main.java.com.exercise.week_020;

/**
 * @Author cl
 * @Date 2021/6/8 21:01
 * @Version 1.0
 */
public class LeetCode_1049_1_最后一块石头的重量II {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int t = sum / 2;
        int[][] f = new int[n + 1][t + 1];
        for (int i = 1; i <= n ; i++) {
            int s = stones[i - 1];
            for (int j = 0; j <= t ; j++) {
                f[i][j] = f[i - 1][j];
                if (j - s >= 0) {
                    f[i][j] =Math.max(f[i][j], f[i - 1][j - s] + s);
                }
            }
        }
        return Math.abs(sum - f[n][t] - f[n][t]);
    }
}
