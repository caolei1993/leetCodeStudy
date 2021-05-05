package main.java.com.exercise.week_010;

/**
 * @Author cl
 * @Date 2021/4/13 15:23
 * @Version 1.0
 */
public class LeetCode_74_2_搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int value = matrix[mid / m][mid % m];
            if (target > value) {
                l = mid + 1;
            } else if (target < value) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
