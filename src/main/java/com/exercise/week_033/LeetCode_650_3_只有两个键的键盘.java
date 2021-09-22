package main.java.com.exercise.week_033;

/**
 * @Author cl
 * @Date 2021/9/22 15:40
 * @Version 1.0
 */
public class LeetCode_650_3_只有两个键的键盘 {
    static final int INF = 0x3f3f3f3f;
    public int minSteps(int n) {
        // f[i][j]代表当前记事本上有i个字符，粘贴板上有j个字符的最小操作次数
        int[][] f = new int[n + 1][n + 1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= n ; j++) {
                f[i][j] = INF;
            }
        }

        f[1][0] = 0;
        f[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = INF;
            for (int j = 0; j <= i / 2 ; j++) {
                f[i][j] = f[i - j][j] + 1;
                min = Math.min(min, f[i][j]);
            }
            f[i][i] = min + 1;
        }
        int ans = INF;
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, f[n][i]);
        }
        return ans;
    }
}
