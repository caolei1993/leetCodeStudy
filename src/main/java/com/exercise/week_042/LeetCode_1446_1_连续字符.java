package main.java.com.exercise.week_042;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/1 4:43 下午
 */
public class LeetCode_1446_1_连续字符 {
    public int maxPower(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n;) {
            int count = 1;
            char c = s.charAt(i);
            while (++i < n && s.charAt(i) == c) {
                count++;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
