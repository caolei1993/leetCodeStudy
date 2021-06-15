package main.java.com.exercise.week_020;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/6/15 14:23
 * @Version 1.0
 */
public class LeetCode_279_2_完全平方数 {
    public int numSquares(int n) {
        // 定义dp数组，f[j]代表凑成j，需要的完全平方数数量
        int[] f = new int[n + 1];
        Arrays.fill(f, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 1; i * i <= n ; i++) {
            int t = i * i;
            for (int j = t; j <= n; j++) {
                f[j] = Math.min(f[j], f[j - t] + 1);
            }
        }
        return f[n];
    }
}
