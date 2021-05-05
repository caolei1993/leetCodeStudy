package main.java.com.exercise.week_010;

/**
 * @Author cl
 * @Date 2021/3/30 7:32
 * @Version 1.0
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class LeetCode_74_1_搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int length = matrix[i].length;
            if ( matrix[i][0] <= target && target <= matrix[i][length-1]) {
                for (int j = 0; j < length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
            if (matrix[i][0] > target) {
                return false;
            }
        }
        return false;
    }
}
