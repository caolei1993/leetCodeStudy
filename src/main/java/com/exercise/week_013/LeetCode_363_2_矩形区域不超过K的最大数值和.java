package main.java.com.exercise.week_013;

import java.util.TreeSet;

/**
 * @Author cl
 * @Date 2021/4/23 10:07
 * @Version 1.0
 */
public class LeetCode_363_2_矩形区域不超过K的最大数值和 {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        // 初始化二维前缀和
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // 二分查找因该使用在数值较大的行或列中
        boolean isRight = m >= n;

        for (int i = 1; i <= (isRight ? n : m) ; i++) {
            for (int j = i; j <= (isRight ? n : m) ; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                // 放入一个0,代表左边界为原矩阵的左边界
                set.add(0);
                // 遍历矩形右边界
                for (int l = 1; l <= (isRight ? m : n) ; l++) {
                    // 求取右边界到最左与上下边界组成矩形的面积
                    int right = sum[j][l] - sum[i - 1][l];
                    Integer left = set.ceiling(right - k);
                    if (left != null) {
                        int cur = right - left;
                        if (cur == k) {
                            return k;
                        }
                        ans = Math.max(ans, cur);
                    }
                    set.add(right);
                }
            }
        }
        return ans;
    }
}
