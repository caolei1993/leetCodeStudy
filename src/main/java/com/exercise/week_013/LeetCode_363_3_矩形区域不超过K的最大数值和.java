package main.java.com.exercise.week_013;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @Author cl
 * @Date 2021/4/23 10:15
 * @Version 1.0
 */
public class LeetCode_363_3_矩形区域不超过K的最大数值和 {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        // 二分查找因该使用在数值较大的行或列中
        boolean isRight = m >= n;
        // 初始化前缀和一维数组（将二维数组简化为一维数组）
        int[] sum = isRight ? new int[m + 1] : new int[n + 1];

        for (int i = 1; i <= (isRight ? n : m) ; i++) {
            // 确认一个边界后，初始化一维数组
            Arrays.fill(sum, 0);
            for (int j = i; j <= (isRight ? n : m) ; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                // 放入一个0,包含边界值
                set.add(0);
                int a = 0;
                // 遍历原矩阵较大维度的数据
                for (int l = 1; l <= (isRight ? m : n) ; l++) {
                    // 由于遍历m,n中较小维度的下界变化，累计求取一维数组当前位置的值
                    sum[l] += isRight ? matrix[l - 1][j - 1] : matrix[j - 1][l - 1];
                    // 求取right的值
                    a += sum[l];
                    Integer left = set.ceiling(a - k);
                    if (left != null) {
                        int cur = a - left;
                        if (cur == k) {
                            return k;
                        }
                        ans = Math.max(ans, cur);
                    }
                    set.add(a);
                }
            }
        }
        return ans;
    }
}
