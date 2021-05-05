package main.java.com.exercise.week_010;

/**
 * @Author cl
 * @Date 2021/4/3 18:00
 * @Version 1.0
 */
public class LeetCode_1143_1_最长公共子序列 {
    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        text1 = " " + text1;
        text2 = " " + text2;
        int[][] f = new int[length1 + 1][length2 + 1];
        for (int i = 0; i < length1 + 1; i++) {
            f[i][0] = 1;
        }
        for (int i = 0; i <length2 ; i++) {
            f[0][i] = 1;
        }
        for (int i = 1; i < length1 + 1 ; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i][j - 1], f[i - 1][j]);
                }
            }
        }
        return f[length1][length2] - 1;
    }
}
