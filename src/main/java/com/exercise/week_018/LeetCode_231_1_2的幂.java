package main.java.com.exercise.week_018;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/30 15:52
 */
public class LeetCode_231_1_2的幂 {
    public boolean isPowerOfTwo(int n) {
        if (n < 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}
