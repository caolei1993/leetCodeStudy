package main.java.com.exercise.week_033;

/**
 * @Author cl
 * @Date 2021/9/22 15:23
 * @Version 1.0
 */
public class LeetCode_650_2_只有两个键的键盘 {
    static int N = 1010;
    static int[] g = new int[N];
    static {
        for (int i = 2; i < N; i++) {
            int cnt = 0;
            int n = i;
            for (int j = 2; j * j <= n ; j++) {
                while (n % j == 0) {
                    cnt += j;
                    n /= j;
                }
            }
            if (n != 1) {
                cnt += n;
            }
            g[i] = cnt;
        }
    }

    public int minSteps(int n) {
        return g[n];
    }
}
