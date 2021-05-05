package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/10 9:20
 * @Version 1.0
 * https://leetcode-cn.com/problems/ugly-number/
 */
public class LeetCode_263_1_丑数 {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}
