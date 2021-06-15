package main.java.com.exercise.week_1_dp_path;

/**
 * @Author cl
 * @Date 2021/6/15 15:39
 * @Version 1.0
 */
public class LeetCode_62_1_不同路径 {
    public int uniquePaths(int m, int n) {
        // 定义状态，f[i][j]代表到坐标为[i,j]位置的方案数
        int[][] f = new int[m][n];
        // dp数组初始化
        f[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                } else if (i > 0) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = f[i][j - 1];
                }

            }
        }
        return f[m - 1][n - 1];
    }
}
