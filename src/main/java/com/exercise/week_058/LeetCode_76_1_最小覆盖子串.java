package main.java.com.exercise.week_058;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/16 20:21
 */
public class LeetCode_76_1_最小覆盖子串 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int tLen = t.length();
        int sLen = s.length();
        int index = 0, min = Integer.MAX_VALUE;
        int count;
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        count = map.size();
        Integer value;
        int p1 = 0, p2 = 0;
        for (;p2 < sLen; p2++) {
            char c = s.charAt(p2);
            value = map.get(c);
            if (value == null) {
                continue;
            }
            map.put(c, value - 1);
            if (value == 1) {
                count--;
            }
            while (count == 0) {
                if (p2 - p1 + 1 < min) {
                    min = p2 - p1 + 1;
                    index = p1;
                }
                char m = s.charAt(p1);
                value = map.get(m);
                p1++;
                if (value == null) {
                    continue;
                }
                map.put(m, value + 1);
                if (value == 0) {
                    count++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(index, index + min);
    }
}
