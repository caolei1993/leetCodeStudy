package main.java.com.exercise.week_029;

/**
 * @Author cl
 * @Date 2021/8/13 10:45
 * @Version 1.0
 */
public class LeetCode_233_1_数字1的个数 {
    public int countDigitOne(int n) {
        int ans = 0;
        for (long mult = 1; mult <= n ; mult *= 10) {
            ans += (n / (mult * 10)) * mult + Math.min(Math.max(n % (mult * 10) - mult + 1, 0), mult);
        }
        return ans;
    }
}
