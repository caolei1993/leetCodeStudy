package main.java.com.exercise.week_039;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/10/28 20:35
 * @Version 1.0
 */
public class LeetCode_301_2_删除无效的括号 {
    Set<String> set = new HashSet<>();
    int n, len, max;
    String str;
    public List<String> removeInvalidParentheses(String s) {
        n = s.length();
        str = s;
        // 定义需要删除的左括号数量及右括号数量
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l > 0) {
                    // 代表括号匹配上了
                    l--;
                } else {
                    // 代表有多余的右括号
                    r++;
                }
            }
        }
        len = n - l - r;

        int l1 = 0, r1 = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                l1++;
            } else if (c == ')') {
                r1++;
            }
        }
        max = Math.min(l1, r1);
        dfs(0, "", l, r, 0);
        return new ArrayList<>(set);
    }

    /**
     *
     * @param u 遍历的下标
     * @param s 当前的字符串
     * @param l 剩余需要删除的左括号数量
     * @param r 剩余需要删除的右括号数量
     * @param score 分数
     */
    private void dfs(int u, String s, int l, int r, int score) {
        if (l < 0 || r < 0 || score < 0 || score > max) {
            return;
        }
        if (l == 0 && r == 0) {
            if (s.length() == len) {
                set.add(s);
            }
        }
        if (u == n) {
            return;
        }
        char c = str.charAt(u);
        if (c == '(') {
            dfs(u + 1, s + c, l, r, score + 1);
            dfs(u + 1, s, l - 1, r, score);
        } else if (c == ')') {
            dfs(u + 1, s + c, l, r, score - 1);
            dfs(u + 1, s, l, r - 1, score);
        } else {
            dfs(u + 1, s + c, l, r, score);
        }
    }
}
