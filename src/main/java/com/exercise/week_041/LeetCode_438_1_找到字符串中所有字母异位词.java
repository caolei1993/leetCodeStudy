package main.java.com.exercise.week_041;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_438_1_找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        // 统计p的词频
        int[] cnt = new int[26];
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }
        int[] cnt1 = new int[26];
        List<Integer> ans = new ArrayList<>();
        for (int l = 0, r = 0; r < n; r++) {
            cnt1[s.charAt(r) - 'a']++;
            if (r - l + 1 > m) {
                cnt1[s.charAt(l) - 'a']--;
                l++;
            }
            if (check(cnt, cnt1)) {
                ans.add(l);
            }
        }

        return ans;

    }

    private boolean check(int[] cnt, int[] cnt1) {
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != cnt1[i]) {
                return false;
            }
        }
        return true;
    }

}
