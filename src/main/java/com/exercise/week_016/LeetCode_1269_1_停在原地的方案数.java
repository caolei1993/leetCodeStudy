package main.java.com.exercise.week_016;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/13 20:35
 */
public class LeetCode_1269_1_停在原地的方案数 {
    public int numWays(int steps, int arrLen) {
        int m = (int)1e9 + 7;
        int max = Math.min(steps / 2, arrLen - 1);
        // f[i][j]表示剩余i步，所在位置在j的位置的所有方案数
        int[][] f = new int[steps + 1][max + 1];
        // 初始化数据
        f[steps][0] = 1;
        // 遍历步数
        for (int i = steps - 1; i >= 0 ; i--) {
            for (int j = 0; j <= max; j++) {
                f[i][j] = f[i + 1][j] % m;
                if (j - 1 >= 0) {
                    f[i][j] = (f[i][j] + f[i + 1][j - 1]) % m;
                }
                if (j + 1 <= max) {
                    f[i][j] = (f[i][j] + f[i + 1][j + 1]) % m;
                }
            }
        }
        return f[0][0];
    }
}
