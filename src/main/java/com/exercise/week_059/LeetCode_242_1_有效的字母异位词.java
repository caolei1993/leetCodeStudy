package main.java.com.exercise.week_059;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/27 13:46
 */
public class LeetCode_242_1_有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        for (char c : sArr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : tArr) {
            if (!map.containsKey(c)) {
                return false;
            }
            int count = map.get(c);
            --count;
            if (count < 0) {
                return false;
            }
            if (count == 0) {
                map.remove(c);
            } else {
                map.put(c, count);
            }
        }
        return map.isEmpty();
    }
}
