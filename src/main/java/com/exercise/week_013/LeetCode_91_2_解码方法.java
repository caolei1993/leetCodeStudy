package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/21 20:57
 * @Version 1.0
 */
public class LeetCode_91_2_解码方法 {
    public int numDecodings(String s) {
        s = " " + s;
        int length = s.length();
        int[] dp = new int[3];
        dp[0] = 1;
        char[] ch = s.toCharArray();
        for (int i = 1; i < length; i++) {
            dp[i % 3] = 0;
            int a = ch[i] - '0', b = (ch[i - 1] - '0') * 10 + (ch[i] - '0');
            if (a >= 1 && a <= 9) {
                dp[i % 3] = dp[(i - 1) % 3];
            }
            if (b >= 10 && b <= 26) {
                dp[i % 3] += dp[(i - 2) % 3];
            }
        }
        return dp[(length - 1) % 3];
    }
}
