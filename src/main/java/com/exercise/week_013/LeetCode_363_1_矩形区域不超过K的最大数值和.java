package main.java.com.exercise.week_013;

import java.util.TreeSet;

/**
 * @Author cl
 * @Date 2021/4/22 9:40
 * @Version 1.0
 * https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
 */
public class LeetCode_363_1_矩形区域不超过K的最大数值和 {
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
        // 固定矩形上边界
        for (int i = 1; i <= m ; i++) {
            // 固定矩形下边界
            for (int j = i; j <= m ; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                // 放入一个0,包含右边界与原矩阵左边界组成矩阵的值
                set.add(0);
                // 遍历矩形右边界
                for (int l = 1; l <= n ; l++) {
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

    public static void main(String[] args) {
        int[][] a = new int[1][3];
        a[0] = new int[]{2,2,-1};
        System.out.println(maxSumSubmatrix(a, 3));
    }
}
