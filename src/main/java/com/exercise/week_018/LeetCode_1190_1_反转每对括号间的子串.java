package main.java.com.exercise.week_018;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/26 20:49
 */
public class LeetCode_1190_1_反转每对括号间的子串 {
    public String reverseParentheses(String s) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        List<Character>  subList;
        for (char c : s.toCharArray()) {
            if (c != ')') {
                deque.offerLast(c);
            } else {
                subList = new ArrayList<>();
                while (deque.peekLast() != '(') {
                    subList.add(deque.pollLast());
                }
                // 去除‘（’
                deque.pollLast();
                // 将反转的字符串放回deque（从后往前取值放入集合，再从前往后放入队列，相当于已经做了反转）
                for (char cc : subList) {
                    deque.offerLast(cc);
                }
            }
        }
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
