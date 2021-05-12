package main.java.com.exercise.week_015;

/**
 * @Author cl
 * @Date 2021/5/5 21:53
 * @Version 1.0
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class LeetCode_7_1_整数反转 {
    public int reverse(int x) {
        long ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return (int)ans == ans ? (int)ans : 0;
    }
}
