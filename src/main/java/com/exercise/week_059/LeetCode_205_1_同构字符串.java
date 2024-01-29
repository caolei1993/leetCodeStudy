package main.java.com.exercise.week_059;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/25 13:32
 */
public class LeetCode_205_1_同构字符串 {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);
            if ((sToT.containsKey(x) && sToT.get(x) != y) || (tToS.containsKey(y) && tToS.get(y) != x)) {
                return false;
            }
            sToT.put(x, y);
            tToS.put(y, x);
        }
        return true;
    }
}
