package main.java.com.exercise.week_1_dp_path;

/**
 * @Author cl
 * @Date 2021/6/15 16:11
 * @Version 1.0
 */
public class LeetCode_63_1_不同路径II {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        f[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    if (i > 0 && j > 0) {
                        f[i][j] = f[i - 1][j] + f[i][j - 1];
                    } else if (i > 0) {
                        f[i][j] = f[i - 1][j];
                    } else if (j > 0) {
                        f[i][j] = f[i][j - 1];
                    }
                }
            }
        }
        return f[m - 1][n - 1];
    }
}
