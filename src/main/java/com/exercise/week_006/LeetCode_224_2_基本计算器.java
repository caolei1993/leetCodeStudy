package main.java.com.exercise.week_006;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/2/22 11:03
 * @Version 1.0
 */
public class LeetCode_224_2_基本计算器 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        // 最终结果
        int ans = 0;
        // 辅助累计求某个数字的值
        int num = 0;
        // 符号位，初始为1代表+
        int  sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            boolean digit = Character.isDigit(a);
            if (digit) {
                num = num * 10 + (a - '0');
            } else if (a == '+') {
                ans += sign * num;
                sign = 1;
                num = 0;
            } else if (a == '-') {
                ans += sign * num;
                sign = -1;
                num = 0;
            } else if (a == '(') {
                stack.push(ans);
                stack.push(sign);
                sign = 1;
                ans = 0;
            } else if (a == ')') {
                ans += sign * num;
                ans = ans * stack.pop();
                ans += stack.pop();
                num = 0;
            }
        }
        if (num != 0) {
            ans += sign * num;
        }
        return ans;
    }

}
