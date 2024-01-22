package main.java.com.exercise.week_058;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/18 17:46
 */
public class LeetCode_54_1_螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = n - 1;
        int high = 0, down = m - 1;
        List<Integer> ans = new ArrayList<>();

        while (true) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[high][i]);
            }
            if (++high > down) {
                break;
            }
            for (int i = high; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if (--right < left) {
                break;
            }
            for (int i = right; i >= left ; i--) {
                ans.add(matrix[down][i]);
            }
            if (--down < high) {
                break;
            }
            for (int i = down; i >= high ; i--) {
                ans.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return ans;
    }
}
