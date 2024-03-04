package main.java.com.exercise.week_057;

import java.util.HashSet;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/12 17:43
 */
public class LeetCode_3_1_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        int l = 0, r = 0;
        int ans = 0;
        while (r < n) {
            char c = s.charAt(r);
            if (!set.contains(c)) {
                set.add(c);
                ans = Math.max(ans, r - l + 1);
            } else {
                while (set.contains(c)) {
                    char m = s.charAt(l);
                    set.remove(m);
                    l++;
                }
                set.add(c);
            }
            r++;
        }

        return ans;
    }
}
