package main.java.com.exercise.week_029;

/**
 * @Author cl
 * @Date 2021/8/12 9:29
 * @Version 1.0
 */
public class LeetCode_516_1_最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // f[i][j]代表下标i到j的子字符串中包含的最大的回文子序列长度
        int[][] f = new int[n][n];

        for (int i = n - 1; i >= 0 ; i--) {
            // 包含单个字符为长度为1的回文
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }
}
