package main.java.com.exercise.week_047;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/17 10:12 下午
 */
public class LeetCode_1220_1_统计元音字母序列的数目 {
    int MOD = (int)1e9 + 7;
    public int countVowelPermutation(int n) {
        // f[i][j]代表长度为i+1的字符串，结尾为j(元音字符的下标)的总的方案数
        long[][] f = new long[n][5];
        Arrays.fill(f[0], 1);
        for (int i = 0; i < n - 1; i++) {
            // 每个元音 'a' 后面都只能跟着 'e'
            f[i + 1][1] += f[i][0];

            // 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
            f[i + 1][0] += f[i][1];
            f[i + 1][2] += f[i][1];

            // 每个元音 'i' 后面 不能 再跟着另一个 'i'
            f[i + 1][0] += f[i][2];
            f[i + 1][1] += f[i][2];
            f[i + 1][3] += f[i][2];
            f[i + 1][4] += f[i][2];

            // 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
            f[i + 1][2] += f[i][3];
            f[i + 1][4] += f[i][3];

            // 每个元音 'u' 后面只能跟着 'a'
            f[i + 1][0] += f[i][4];
            for (int j = 0; j < 5; j++) {
                f[i + 1][j] %= MOD;
            }
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans += f[n - 1][i];
        }
        return (int)(ans % MOD);
    }
}
