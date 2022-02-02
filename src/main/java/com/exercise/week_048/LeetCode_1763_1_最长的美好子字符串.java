package main.java.com.exercise.week_048;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/1 9:53 下午
 */
public class LeetCode_1763_1_最长的美好子字符串 {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 > ans.length() && check(s.substring(i, j + 1))) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    private boolean check(String str) {
        Set<Character> set = new HashSet<>();
        char[] charStr = str.toCharArray();
        for (char c : charStr) {
            set.add(c);
        }
        for (char c : charStr) {
            char a = Character.toLowerCase(c);
            char b = Character.toUpperCase(c);
            if (!set.contains(a) || !set.contains(b)) {
                return false;
            }
        }
        return true;
    }
}
