package main.java.com.exercise.week_006;

import java.util.Stack;

/**
 * @Author cl
 * @Date 2021/3/1 16:48
 * @Version 1.0
 * https://leetcode-cn.com/problems/basic-calculator-iii/
 */
public class LeetCode_772_基本计算器III {

    public int calculate(String s) {
        s = s.trim();
        char[] arr = s.toCharArray();
        Stack<Integer> stack =  new Stack<>();
        int ans = 0;
        // 辅助求多位数数字
        int num = 0;
        // 遍历下标
        int index = 0;
        char sigh = '+';
        int length = arr.length;
        while (index < length) {
            // 多位数数字求值
            if (Character.isDigit(arr[index])) {
                num += num * 10 + (arr[index] - '0');
            }

            // 如果是左括号，整体看做是一个值
            if (arr[index] == '(') {
                int range = findClosing(s.substring(index));
                // 递归求括号中的值
                num = calculate(s.substring(index+1, index+range));
                // 坐标值调整
                index += range;
            }

            if ((index == length - 1 || !Character.isDigit(arr[index])) && arr[index] != ' ') {
                // 不是数字和括号，就是 + - * /
                switch (sigh) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        num = num * stack.pop();
                        stack.push(num);
                        break;
                    case '/':
                        num = stack.pop() / num;
                        stack.push(num);
                        break;
                    default:
                }
                num = 0;
                sigh = arr[index];
            }
            index++;
        }

        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    private int findClosing(String s) {
        int value = 0;
        int index = 0;
        while (true) {
            if (s.charAt(index) == '(') {
                value++;
            } else if (s.charAt(index) == ')') {
                value --;
                if (value == 0) {
                    return index;
                }
            }
            index++;
        }
    }

    public static void main(String[] args) {
        LeetCode_772_基本计算器III a = new LeetCode_772_基本计算器III();
        System.out.println("ans = " + a.calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
    }
}
