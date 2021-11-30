package main.java.com.exercise.week_028;

/**
 * @Author cl
 * @Date 2021/8/8 19:01
 * @Version 1.0
 */
public class LeetCode_1137_1_第N个泰波那契数 {
    public int tribonacci(int n) {
        int a = 0, b = 1, c = 1;
        switch (n) {
            case 0:
                return a;
            case 1:
                return b;
            case 2:
                return c;
            default:
                for (int i = 3; i <= n ; i++) {
                    int d = a + b + c;
                    a = b;
                    b = c;
                    c = d;
                }
                return c;
        }
    }
}
