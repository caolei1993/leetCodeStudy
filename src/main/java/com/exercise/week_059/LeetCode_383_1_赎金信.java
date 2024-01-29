package main.java.com.exercise.week_059;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/24 16:53
 */
public class LeetCode_383_1_赎金信 {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] ranArr = ransomNote.toCharArray();
        char[] magArr = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magArr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : ranArr) {
            if (!map.containsKey(c)) {
                return false;
            }
            int count = map.get(c);
            count--;
            map.put(c, count);
            if (count < 0) {
                return false;
            }
        }
        return true;
    }
}
