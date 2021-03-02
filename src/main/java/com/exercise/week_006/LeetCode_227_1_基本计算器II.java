package main.java.com.exercise.week_006;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/3/1 13:38
 * @Version 1.0
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 */
public class LeetCode_227_1_基本计算器II {
    public int calculate(String s) {
        // 添加等号，方便处理字符串最后一个数字
        s = s + '=';
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int num = 0;
        char sigh = '+';
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                num = num * 10 + (arr[i] - '0');
            } else if (arr[i] != ' '){
                // 不是数字，就是 + - * /
                switch (sigh) {
                    case '+':
                        stack.push(num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    case '-':
                        stack.push(-num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    case '*':
                        num = num * stack.pop();
                        stack.push(num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    case '/':
                        num = stack.pop() / num;
                        stack.push(num);
                        num = 0;
                        sigh = arr[i];
                        break;
                    default:
                }
            }
        }
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_227_1_基本计算器II a = new LeetCode_227_1_基本计算器II();
        System.out.println(a.calculate(" 3+2* 2/2"));
    }
}
