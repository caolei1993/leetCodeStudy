package main.java.com.exercise.week_019;

/**
 * @Author cl
 * @Date 2021/5/31 21:20
 * @Version 1.0
 */
public class LeetCode_342_2_4的幂 {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & -n) == n && (n & 0xaaaaaaaa) == 0;
    }
}
