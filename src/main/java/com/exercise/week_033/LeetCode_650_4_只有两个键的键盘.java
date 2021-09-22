package main.java.com.exercise.week_033;

/**
 * @Author cl
 * @Date 2021/9/22 16:02
 * @Version 1.0
 */
public class LeetCode_650_4_只有两个键的键盘 {
    public int minSteps(int n) {
        // f[i]代表记事本上有i个字符，所需要的最少操作次数
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    f[i] = Math.min(f[i], f[j] + i / j);
                    f[i] = Math.min(f[i], f[i/j] + j);
                }
            }
        }
        return f[n];
    }
}
