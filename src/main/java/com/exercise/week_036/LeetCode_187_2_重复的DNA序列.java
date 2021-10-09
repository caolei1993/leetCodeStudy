package main.java.com.exercise.week_036;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/10/9 17:02
 * @Version 1.0
 */
public class LeetCode_187_2_重复的DNA序列 {
    static final int L = 10;
    Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n < L) {
            return ans;
        }
        int val = 0;
        // 求取前9个字符的值
        for (int i = 0; i < L - 1; i++) {
            val = (val << 2) | map.get(s.charAt(i));
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i + L <= n; i++) {
            val = ((val << 2) | map.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            int c = cnt.getOrDefault(val, 0);
            if (c == 1) {
                ans.add(s.substring(i, i + L));
            }
            cnt.put(val, c + 1);
        }

        return ans;
    }
}
