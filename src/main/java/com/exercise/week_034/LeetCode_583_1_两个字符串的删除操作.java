package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/25 16:01
 * @Version 1.0
 */
public class LeetCode_583_1_两个字符串的删除操作 {
    public int minDistance(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int n = s1.length, m = s2.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = 1;
        }

        for (int j = 0; j <= m; j++) {
            f[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                }
            }
        }
        int max = f[n][m] - 1;
        return n - max + m - max;
    }
}
