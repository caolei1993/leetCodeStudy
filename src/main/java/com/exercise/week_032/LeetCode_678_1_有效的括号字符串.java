package main.java.com.exercise.week_032;

/**
 * @Author cl
 * @Date 2021/9/12 17:33
 * @Version 1.0
 */
public class LeetCode_678_1_有效的括号字符串 {
    public boolean checkValidString(String s) {
        int n = s.length();
        // 状态定义，f[i][j]表示前i个字符（字符下标从1开始），能否与j个右括号形成合法的括号序列
        boolean[][] f = new boolean[n + 1][n + 1];
        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            for (int j = 0; j <= i; j++) {
                if (c == '(') {
                    if (j - 1 >= 0) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                } else if (c == ')') {
                    if (j + 1 <= i) {
                        f[i][j] = f[i - 1][j + 1];
                    }
                } else {
                    f[i][j] = f[i - 1][j];
                    if (j - 1 >= 0) {
                        f[i][j] |= f[i - 1][j - 1];
                    }
                    if (j + 1 <= i) {
                        f[i][j] |= f[i - 1][j + 1];
                    }
                }
            }
        }
        return f[n][0];
    }
}
