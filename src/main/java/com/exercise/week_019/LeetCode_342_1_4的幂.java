package main.java.com.exercise.week_019;

/**
 * @Author cl
 * @Date 2021/5/31 20:37
 * @Version 1.0
 */
public class LeetCode_342_1_4的幂 {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        int x = (int)Math.sqrt(n);
        return x * x == n && (x & -x) == x;
    }
}
