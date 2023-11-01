package main.java.com.exercise.week_001;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/15 11:09 上午
 */
public class Test_4 {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<int[]> list = new ArrayList<>();
        for (String word : words) {
            int[] cnt = new int[26];
            for (char c : word.toCharArray()) {
                cnt[c - 'a']++;
            }
            list.add(cnt);
        }
        List<String> ans = new ArrayList<>();
        out : for (int i = 0, s = i; i < n;) {
            int[] cnt1 = list.get(i);
            ans.add(words[i]);
            while(++s < n) {
                if (!check(cnt1, list.get(s))) {
                    i = s;
                    continue out;
                }
            }
            i = s;
        }
        return ans;
    }

    private boolean check(int[] cnt1, int[] cnt2) {
        for (int j = 0; j < 26; j++) {
            if (cnt1[j] != cnt2[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Test_4 test = new Test_4();
        System.out.println(test.removeAnagrams(new String[]{"a","b","c","d","e"}));
    }
}
