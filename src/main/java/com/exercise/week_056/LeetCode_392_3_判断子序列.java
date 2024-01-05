package main.java.com.exercise.week_056;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/4 20:52
 */
public class LeetCode_392_3_判断子序列 {
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] f = new int[m + 1][26];

        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0 ; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == 'a' + j) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (f[index][s.charAt(i)  - 'a'] == m) {
                return false;
            }
            index = f[index][s.charAt(i)  - 'a'] + 1;
        }
        return true;
    }
}
