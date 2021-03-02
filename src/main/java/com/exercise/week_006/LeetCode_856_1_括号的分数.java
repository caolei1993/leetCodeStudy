package main.java.com.exercise.week_006;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/2/9 15:29
 * @Version 1.0
 * https://leetcode-cn.com/problems/score-of-parentheses/
 */
public class LeetCode_856_1_括号的分数 {
    public int scoreOfParentheses(String S) {
        String left = "(";
        Stack<Integer> stack = new Stack<>();
        // 当前分数
        stack.push(0);
        for (int i=0; i < S.length(); i++) {
            String str = String.valueOf(S.charAt(i));
            if (str.equals(left)) {
                stack.push(0);
            } else {
                int m = stack.pop();
                int n = stack.pop();
                stack.push(n + (Math.max(2*m, 1)));
            }
        }
        return stack.pop();
    }
}
