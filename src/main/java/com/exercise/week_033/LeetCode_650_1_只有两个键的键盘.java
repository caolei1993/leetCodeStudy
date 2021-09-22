package main.java.com.exercise.week_033;

/**
 * @Author cl
 * @Date 2021/9/19 19:19
 * @Version 1.0
 */
public class LeetCode_650_1_只有两个键的键盘 {
    public int minSteps(int n) {
        int ans = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ans += i;
                n /= i;
            }
        }
        if (n != 1) {
            ans += n;
        }
        return ans;
    }
}
