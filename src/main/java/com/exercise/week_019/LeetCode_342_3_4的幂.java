package main.java.com.exercise.week_019;

/**
 * @Author cl
 * @Date 2021/5/31 21:27
 * @Version 1.0
 */
public class LeetCode_342_3_4的幂 {
    public boolean isPowerOfFour(int n) {
        return (n & -n) == n && n % 3 == 1;
    }
}
