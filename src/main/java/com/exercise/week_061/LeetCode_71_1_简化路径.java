package main.java.com.exercise.week_061;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/28 17:38
 */
public class LeetCode_71_1_简化路径 {
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Deque<String> deque = new LinkedList<>();
        for (String str : arr) {
            if (".".equals(str) || "".equals(str)) {
            } else if ("..".equals(str)) {
                deque.poll();
            } else {
                deque.push(str);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
            sb.append("/");
        }
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }
}
