package main.java.com.exercise.week_059;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/26 18:26
 */
public class LeetCode_290_1_单词规律 {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map1 = new HashMap<>();
        char[] pArr = pattern.toCharArray();
        String[] sArr = s.split(" ");
        if (pArr.length != sArr.length) {
            return false;
        }
        for (int i = 0; i < pArr.length; i++) {
            char c = pArr[i];
            String word = sArr[i];
            if ((map.containsKey(c) && !map.get(c).equals(word)) || (map1.containsKey(word) && map1.get(word) != c)) {
                return false;
            }
            map.put(c, word);
            map1.put(word, c);
        }
        return true;
    }
}
