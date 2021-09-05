package main.java.com.exercise.week_031;

/**
 * @Author cl
 * @Date 2021/9/5 21:18
 * @Version 1.0
 */
public class LeetCode_剑指Offer10_1_斐波那契数列 {
    int mod = (int) 1e9 + 7;
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            c = c % mod;
            a = b;
            b = c;
        }
        return b;
    }
}
