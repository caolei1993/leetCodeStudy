package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/23 10:46
 * @Version 1.0
 */
public class LeetCode_326_1_3的幂 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
