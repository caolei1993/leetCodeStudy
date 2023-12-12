package main.java.com.exercise.week_054;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/11 19:09
 */
public class LeetCode_13_1_罗马数子转整数 {
    Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int len = s.length();
        int sum = 0;
        for (int i = 0; i < len - 1; i++) {
            char cur = s.charAt(i);
            char next = s.charAt(i + 1);
            if (map.get(cur) < map.get(next)) {
                sum -= map.get(cur);
            } else {
                sum += map.get(cur);
            }
        }
        return sum + map.get(s.charAt(len - 1));
    }
}
