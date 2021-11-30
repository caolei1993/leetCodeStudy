package main.java.com.exercise.week_032;

/**
 * @Author cl
 * @Date 2021/9/13 17:13
 * @Version 1.0
 */
public class LeetCode_600_1_不含连续1的非负整数 {
    public int findIntegers(int n) {
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, ans = 0;
        for (int i = 29; i >= 0; i--) {
            int v = 1 << i;
            if ((v & n) != 0) {
                if (pre == 0) {
                    ans += dp[i + 1];
                } else {
                    continue;
                }
            }
            if (i == 0) {
                ans++;
            }
        }

        return ans;
    }
}
