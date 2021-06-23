package main.java.com.exercise.week_022;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/6/22 9:29
 * @Version 1.0
 */
public class LeetCode_剑指Offer38_1_字符串的排列 {

    Set<String> set = new HashSet<>();
    boolean[] b = new boolean[10];

    public String[] permutation(String s) {
        dfs(s, 0, "");
        String[] arr = new String[set.size()];
        int idx = 0;
        for (String str : set) {
            arr[idx++] = str;
        }
        return arr;
    }

    private void dfs(String s, int index, String cur) {
        int n = s.length();
        if (index == n) {
            set.add(cur);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!b[i]) {
                b[i] = true;
                dfs(s, index + 1, cur + s.charAt(i));
                b[i] = false;
            }
        }
    }
}
