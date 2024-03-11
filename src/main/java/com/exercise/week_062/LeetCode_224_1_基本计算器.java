package main.java.com.exercise.week_062;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/3/5 17:58
 */
public class LeetCode_224_1_基本计算器 {
    public int calculate(String s) {
        Deque<Integer> deque = new LinkedList<>();
        int n = s.length();
        int i = 0;
        deque.push(1);
        int sign = 1;
        int ans = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (c == '+') {
                sign = deque.peek();
                i++;
            } else if (c == '-') {
                sign = -deque.peek();
                i++;
            } else if (c == '(') {
                deque.push(sign);
                i++;
            } else if (c == ')') {
                deque.pop();
                i++;
            } else {
                int num = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                i++;
                ans += num * sign;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "1 + 1";
        System.out.println(new LeetCode_224_1_基本计算器().calculate(s));
    }
}
