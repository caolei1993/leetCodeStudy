package main.java.com.exercise.week_018;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/26 21:36
 */
public class LeetCode_1190_2_反转每对括号间的子串 {
    char[] deque = new char[2000];
    int head = 0, tail = -1;
    char[] sub = new char[2000];
    public String reverseParentheses(String s) {
        for (char c : s.toCharArray()) {
            if (c == ')') {
                int ids = 0;
                while (tail >= head) {
                    if (deque[tail] == '(') {
                        tail--;
                        for (int i = 0; i < ids; i++) {
                            deque[++tail] = sub[i];
                        }
                        break;
                    } else {
                        sub[ids++] = deque[tail--];
                    }
                }
            } else {
                deque[++tail] = c;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (tail >= head) {
            sb.append(deque[head++]);
        }
        return sb.toString();
    }
}
