package main.java.com.exercise.week_039;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/10/27 17:32
 * @Version 1.0
 */
public class LeetCode_301_1_删除无效的括号 {
    /**
     * 存储答案，set用来去重
     */
    Set<String> set = new HashSet<>();
    /**
     * n代表字符串长度，max代表最大的括号对数，len代表合法的字符的长度
     */
    int n, max, len;
    /**
     * 目标字符串
     */
    String str;
    public List<String> removeInvalidParentheses(String s) {
        str = s;
        n = s.length();
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else if (c == ')') {
                r++;
            }
        }
        max = Math.min(l, r);
        dfs(0, "", 0);
        return new ArrayList<>(set);
    }

    /**
     *
     * @param u 当前遍历的坐标
     * @param cur 当前字符串
     * @param score 分数
     */
    private void dfs(int u, String cur, int score) {
        if (score < 0 || score > max) {
            return;
        }
        if (u == n) {
            if (score == 0 && cur.length() >= len) {
                if (cur.length() > len) {
                    set.clear();
                }
                len = cur.length();
                set.add(cur);
            }
            return;
        }
        char c = str.charAt(u);
        if (c == '(') {
            dfs(u + 1, cur + String.valueOf(c), score + 1);
            dfs(u + 1, cur, score);
        } else if (c == ')') {
            dfs(u + 1, cur + String.valueOf(c), score - 1);
            dfs(u + 1, cur, score);
        } else {
            dfs(u + 1, cur + String.valueOf(c), score);
        }
    }
}
