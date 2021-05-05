package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/22 17:03
 * @Version 1.0
 * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 */
public class LeetCode_304_1_二维区域和检索_矩阵不可变 {
    int[][] sum;
    public LeetCode_304_1_二维区域和检索_矩阵不可变(int[][] matrix) {
        // 查询行和列的长度
        int m = matrix.length, n = m == 0 ? 0 : matrix[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] +  matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;
        col1++;
        row2++;
        col2++;
        return sum[row2][col2] - sum[row1 - 1][col2] - sum[row2][col1 - 1] + sum[row1 - 1][col1 - 1];
    }
}
