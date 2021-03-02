package main.java.com.exercise.week_006;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/2/17 9:21
 * @Version 1.0
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class LeetCode_224_1_基本计算器 {
    final static int RIGHT = ')';
    public int calculate(String s) {

        s = s.trim();
        // 兼容 -2 + 1，类似这种表达式
        if (s.charAt(0) == '-') {
            s = '0' + s;
        }
        Stack<Integer> stack = new Stack<>();
        // 数字的初始值
        int num = 0;
        // 用来求某个数字的真实值，123 = 3*1 + 2*10 + 1*100
        int n = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char a = s.charAt(i);
            if (Character.isDigit(a)) {
                num += (a - '0') * n;
                n = n * 10;
                continue;
            }
            if (n != 1) {
                stack.push(num);
                // 还原n以及num的值
                n = 1;
                num = 0;
            }
            if (a == '+') {
                stack.push(1);
                continue;
            }
            if (a == '-') {
                stack.push(-1);
                continue;
            }
            if (a == ')') {
                stack.push(RIGHT);
                continue;
            }
            if (a == '(') {
                int value = calculateExpr(stack);
                // 去除右括号
                stack.pop();
                stack.push(value);
            }
        }
        if (n != 1) {
            stack.push(num);
        }
        return calculateExpr(stack); 

    }

    /**
     * 计算（）表达式的值
     * @param stack
     * @return
     */
    private int calculateExpr(Stack<Integer> stack) {
        int value = 0;
        if (!stack.isEmpty()) {
            value = stack.pop();
        }
        while (stack.size() > 1 && stack.peek() != RIGHT) {
            value += stack.pop() * stack.pop();
        }
        return value;
    }
}
