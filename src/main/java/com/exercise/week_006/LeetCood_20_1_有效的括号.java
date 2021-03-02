package main.java.com.exercise.week_006;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/2/9 9:33
 * @Version 1.0
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class LeetCood_20_1_有效的括号 {
    public boolean isValid(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("(", ")");
        map.put("{", "}");
        map.put("[", "]");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            if (map.containsKey(str)) {
                stack.push(str);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String left = stack.pop();
                if (!map.get(left).equals(str)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
