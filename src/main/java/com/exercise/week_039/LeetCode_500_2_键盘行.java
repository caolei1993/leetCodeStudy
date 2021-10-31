package main.java.com.exercise.week_039;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/10/31 20:49
 * @Version 1.0
 */
public class LeetCode_500_2_键盘行 {
    static String[] str = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    static int[] hash = new int[26];
    static {
        for (int i = 0; i < str.length; i++) {
            char[] chars = str[i].toCharArray();
            for (char c : chars) {
                hash[c - 'a'] = i;
            }
        }
    }
    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        out:
        for (String word : words) {
            char[] chars = word.toCharArray();
            int t = -1;
            for (char c : chars) {
                c = Character.toLowerCase(c);
                if (t == -1) {
                    t = hash[c - 'a'];
                } else {
                    if (t != hash[c - 'a']) {
                        continue out;
                    }
                }
            }
            ans.add(word);
        }
        return ans.toArray(new String[0]);
    }

}
