package main.java.com.exercise.week_019;

/**
 * @Author cl
 * @Date 2021/6/6 19:57
 * @Version 1.0
 */
public class LeetCode_474_1_一和零 {
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        // 定义并初始化记录每个字符串中0和1数量的二维数组
        int[][] cnt = new int[length][2];
        for (int i = 0; i < length; i++) {
            int zero = 0, one = 0;
            String str = strs[i];
            for (char a : str.toCharArray()) {
                if (a == '0') {
                    zero++;
                } else {
                    one++;
                }
                cnt[i] = new int[]{zero, one};
            }
        }

        // f[k][i][j]表示遍历过k个字符串，最多i个0，j个1，所得的分数（选中一个字符串得一分）
        int[][][] f = new int[length + 1][m + 1][n + 1];
        for (int k = 1; k < length + 1; k++) {
            int zero = cnt[k - 1][0], one = cnt[k - 1][1];
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    // 不选第k个字符串
                    int a = f[k - 1][i][j];
                    // 选第k个字符串
                    int b = (i >= zero && j >= one) ? f[k - 1][i - zero][j - one] + 1 : 0;
                    f[k][i][j] = Math.max(a , b);
                }
            }
        }
        return f[length][m][n];
    }
}
