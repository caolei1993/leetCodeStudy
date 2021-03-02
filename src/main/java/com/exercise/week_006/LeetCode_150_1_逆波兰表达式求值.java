package main.java.com.exercise.week_006;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/2/17 9:00
 * @Version 1.0
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class LeetCode_150_1_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String s : tokens) {
            boolean result = s.matches("\\d+");
            if (result) {
                stack.push(s);
            } else {
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(a - b));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(a / b));
                        break;
                    default:
                }
            }

        }
        return Integer.parseInt(stack.pop());
    }
}
