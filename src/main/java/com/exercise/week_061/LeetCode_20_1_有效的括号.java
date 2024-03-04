package main.java.com.exercise.week_061;

import java.util.*;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/27 17:21
 */
public class LeetCode_20_1_有效的括号 {
    List<Character> leftList = new ArrayList<>(Arrays.asList('(', '[', '{'));
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        char[] arr = s.toCharArray();
        Deque<Character> deque = new LinkedList<>();
        for (char c : arr) {
            if (leftList.contains(c)) {
                deque.push(c);
            } else {
                if (deque.isEmpty()) {
                    return false;
                }
                char val = deque.poll();
                if (val != map.get(c)) {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }
}
