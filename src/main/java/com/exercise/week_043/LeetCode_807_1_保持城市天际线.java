package main.java.com.exercise.week_043;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/13 9:14 下午
 */
public class LeetCode_807_1_保持城市天际线 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] row = new int[n];
        int[] col = new int[n];
        // 遍历行
        for (int i = 0; i < n; i++) {
            // 遍历列
            for (int j = 0; j < n; j++) {
                row[i] = Math.max(row[i], grid[i][j]);
                col[j] = Math.max(col[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += Math.min(row[i], col[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
