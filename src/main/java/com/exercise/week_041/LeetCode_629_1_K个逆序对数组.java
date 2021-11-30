package main.java.com.exercise.week_041;

/**
 * @Author cl
 * @Date 2021/11/11 16:32
 * @Version 1.0
 */
public class LeetCode_629_1_K个逆序对数组 {
    int mod = (int) 1e9 + 7;
    public int kInversePairs(int n, int k) {
        // 状态定义，f[i][j]表示[1,i]的恰好包含j个逆序对的数组个数
        int[][] f = new int[n + 1][k + 1];
        f[0][0] = 1;
        // 遍历数组的所有长度
        for (int i = 1; i <= n; i++) {
            // 遍历小于等于k的所有逆序对可能
            for (int j = 0; j <= k; j++) {
                f[i][j] = (j - 1 >= 0 ? f[i][j - 1] : 0) - (j - i >= 0 ? f[i - 1][j - i] : 0) + f[i - 1][j];
                if (f[i][j] >= mod) {
                    f[i][j] -= mod;
                } else if (f[i][j] < 0) {
                    f[i][j] += mod;
                }
            }
        }
        return f[n][k];
    }
}
