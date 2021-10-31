package main.java.com.exercise.week_039;

/**
 * @Author cl
 * @Date 2021/10/25 20:24
 * @Version 1.0
 */
public class LeetCode_240_1_搜索二维矩阵II {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l1 = 0, r1 = n - 1;
        while (l1 < r1) {
            int mid = (l1 + r1 + 1) >> 1;
            if (matrix[0][mid] <= target) {
                l1 = mid;
            } else {
                r1 = mid - 1;
            }
        }
        int l2 = 0, r2 = m - 1;
        while (l2 < r2) {
            int mid = (l2 + r2 + 1) >> 1;
            if (matrix[mid][0] <= target) {
                l2 = mid;
            } else {
                r2 = mid - 1;
            }
        }
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
