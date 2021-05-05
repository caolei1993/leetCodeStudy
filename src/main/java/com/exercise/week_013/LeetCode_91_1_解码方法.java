package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/21 13:38
 * @Version 1.0
 * https://leetcode-cn.com/problems/decode-ways/
 */
public class LeetCode_91_1_解码方法 {
    public int numDecodings(String s) {
        s = " " + s;
        int length = s.length();
        int[] dp = new int[length];
        dp[0] = 1;
        char[] ch = s.toCharArray();
        for (int i = 1; i < length; i++) {
            int a = ch[i] - '0', b = (ch[i - 1] - '0') * 10 + (ch[i] - '0');
            if (a >= 1 && a <= 9) {
                dp[i] = dp[i - 1];
            }
            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[length - 1];
    }
}
