package main.java.com.exercise.week_058;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/19 18:13
 */
public class LeetCode_48_1_旋转图像 {
    public void rotate(int[][] matrix) {
        int m = matrix.length;

        int[][] matrixNew = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrixNew[j][m - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            System.arraycopy(matrixNew[i], 0, matrix[i], 0, m);
        }
    }
}
