package main.java.com.exercise.week_045;

/**
 * @author cl
 * @version 1.0
 * @date 2022/1/1 10:12 下午
 */
public class LeetCode_2022_2_将一维数组转变为二维数组 {
    public int[][] construct2DArray(int[] original, int m, int n) {
        int len = original.length;
        if (len != m * n) {
            return new int[0][0];
        }
        int[][] ans = new int[m][n];
        for (int i = 0, idx = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[idx++];
            }
        }
        return ans;
    }
}
