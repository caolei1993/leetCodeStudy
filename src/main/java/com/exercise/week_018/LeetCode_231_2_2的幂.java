package main.java.com.exercise.week_018;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/30 16:16
 */
public class LeetCode_231_2_2的幂 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
}
