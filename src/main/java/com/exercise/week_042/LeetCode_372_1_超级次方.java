package main.java.com.exercise.week_042;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/5 9:35 上午
 */
public class LeetCode_372_1_超级次方 {
    int mod = 1337;
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    private int dfs (int a, int[] b, int n) {
        if (n == -1) {
            return 1;
        }
        return (qpow(dfs(a, b, n - 1), 10) * qpow(a, b[n])) % mod;
    }
    private int qpow(int a, int b) {
        int val = 1;
        a %= mod;
        while (b != 0) {
            if ((b & 1) == 1) {
                val = val * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return val;
    }
}
