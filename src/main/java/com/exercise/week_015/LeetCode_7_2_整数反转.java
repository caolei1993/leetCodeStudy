package main.java.com.exercise.week_015;

/**
 * @Author cl
 * @Date 2021/5/5 22:01
 * @Version 1.0
 */
public class LeetCode_7_2_整数反转 {
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            if (x > 0 && ans > (Integer.MAX_VALUE - x % 10) / 10) {
                return 0;
            }
            if (x < 0 && ans < (Integer.MIN_VALUE - x % 10) / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }
}
