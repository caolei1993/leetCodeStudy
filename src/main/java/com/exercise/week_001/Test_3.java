package main.java.com.exercise.week_001;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/11 5:04 下午
 */
public class Test_3 {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (s.equals("")) {
            return 0;
        }
        int ans = 1;

        out:for (int i = 0; i < n; i++) {
            in:for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);
                int len = j - i;
                char[] arr = sub.toCharArray();
                Set<Character> set = new HashSet<>();
                for (char c : arr) {
                    set.add(c);
                }
                if (set.size() != len) {
                    continue out;
                } else {
                    ans = Math.max(ans, len);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("au"));
    }
}
