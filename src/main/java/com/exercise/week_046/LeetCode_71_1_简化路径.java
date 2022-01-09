package main.java.com.exercise.week_046;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/9 9:35 下午
 */
public class LeetCode_71_1_简化路径 {
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        int n = path.length();
        for (int i = 0; i < n;) {
            if (path.charAt(i) == '/') {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < n && path.charAt(j) != '/') {
                j++;
            }
            String item = path.substring(i, j);
            if (item.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (!item.equals(".")) {
                deque.addLast(item);
            }
            i = j;
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/").append(deque.pollFirst());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
