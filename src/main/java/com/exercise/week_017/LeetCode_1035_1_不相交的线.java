package main.java.com.exercise.week_017;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/21 20:34
 */
public class LeetCode_1035_1_不相交的线 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m][n];
    }
}
