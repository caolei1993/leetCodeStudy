package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/26 10:41
 * @Version 1.0
 */
public class LeetCode_583_2_两个字符串的删除操作 {
    public int minDistance(String word1, String word2) {
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        int n = c1.length, m = c2.length;
        // 定义状态，f[i][j]表示字符串1的前i个字符，字符串2的前j个字符，修改为相同字符串最少的操作次数
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            f[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 两个字符不同，则取操作一次后取值较小者
                f[i][j] = Math.min(f[i - 1][j] + 1, f[i][j - 1] + 1);
                // 如果两个字符相等，则不需要删除
                if (c1[i - 1] == c2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }
        // 减掉空格哨兵的一个长度
        return f[n][m];
    }
}
