package main.java.com.exercise.week_041;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_438_2_找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        // 词频统计数组
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }
        // 统计p中有多少种字母
        int a = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                a++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int l = 0, r = 0, b = 0; r < n; r++) {
            int i = s.charAt(r) - 'a';
            cnt[i]--;
            if (cnt[i] == 0) {
                b++;
            }
            if (r - l + 1 > m) {
                int j = s.charAt(l) - 'a';
                cnt[j]++;
                l++;
                if (cnt[j] == 1) {
                    b--;
                }
            }
            if (a == b) {
                ans.add(l);
            }
        }
        return ans;

    }
}
