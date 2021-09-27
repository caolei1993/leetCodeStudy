package main.java.com.exercise.week_035;

/**
 * @Author cl
 * @Date 2021/9/27 15:05
 * @Version 1.0
 */
public class LeetCode_639_2_解码方法II {
    static final int mod = (int)1e9 + 7;
    public int numDecodings(String s) {
        int n = s.length();
        long[] f = new long[3];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int v = c - '0';
            long cnt = 0;
            int p1 = (i - 1) % 3, p2 = (i - 2) % 3;
            for (int item = 1; item <= 26; item++) {
                if (item < 10) {
                    // c单独作为一个item
                    if (c == '*' || v == item) {
                        cnt += f[p1];
                    }
                } else {
                    // 与前一位一起组成item
                    if (i - 2 < 0) {
                        break;
                    }
                    char prev = s.charAt(i - 2);
                    int u = prev - '0';
                    int a = item / 10, b = item % 10;
                    if ((prev == '*' || u == a) && ((c == '*' && b != 0) || b == v)) {
                        cnt += f[p2];
                    }
                }
            }
            f[i % 3] = cnt % mod;
        }
        return (int)f[n % 3];
    }
}
