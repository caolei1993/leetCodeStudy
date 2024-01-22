package main.java.com.exercise.week_058;

import java.util.*;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/15 21:58
 */
public class LeetCode_30_1_串联所有单词的子串 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int m = words.length;
        int n = words[0].length();
        int ls = s.length();

        for (int i = 0; i < n; i++) {
            // 不足words中所有单词的长度
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> map = new HashMap<>();
            // 初始化窗口里的单词
            for (int j = 0; j < m; j++) {
                String w = s.substring(i + j * n , i + (j + 1) * n);
                map.put(w, map.getOrDefault(w, 0) + 1);
            }

            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) - 1);
                if (map.get(word) == 0) {
                    map.remove(word);
                }
            }

            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    String w = s.substring(start + (m - 1) * n, start + m * n);
                    map.put(w, map.getOrDefault(w, 0) + 1);
                    if (map.get(w) == 0) {
                        map.remove(w);
                    }

                    w = s.substring(start - n , start);
                    map.put(w, map.getOrDefault(w, 0)  -  1);
                    if (map.get(w) == 0) {
                        map.remove(w);
                    }
                }
                if (map.isEmpty()) {
                    ans.add(start);
                }
            }
        }
        return ans;
    }
}
