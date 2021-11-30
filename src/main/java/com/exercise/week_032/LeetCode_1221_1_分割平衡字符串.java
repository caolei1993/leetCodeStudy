package main.java.com.exercise.week_032;

/**
 * @Author cl
 * @Date 2021/9/7 11:03
 * @Version 1.0
 */
public class LeetCode_1221_1_分割平衡字符串 {
    public int balancedStringSplit(String s) {
        int len = s.length();
        int ans = 0, value = 0;
        for (int i = 0; i < len; i++) {
            int a = 'L' == s.charAt(i) ? 1 : -1;
            value += a;
            if (value == 0) {
                ans++;
            }
        }
        return ans;
    }
}
