package main.java.com.exercise.week_012;

/**
 * @Author cl
 * @Date 2021/4/18 14:51
 * @Version 1.0
 */
public class LeetCode_87_2_打扰字符串 {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        int n = s1.length();
        boolean[][][] f = new boolean[n][n][n + 1];
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        // 处理长度为1的所有情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j][1] = ch1[i] == ch2[j];
            }
        }

        // 处理其余长度
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    for (int k = 1; k < len; k++) {
                        boolean a = f[i][j][k] && f[i + k][j + k][len - k];
                        boolean b = f[i][j + len - k][k] && f[i + k][j][len - k];
                        if (a || b) {
                            f[i][j][len] = true;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
}