package main.java.com.exercise.week_049;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/3/2 11:03 下午
 */
public class LeetCode_564_1_寻找最近的回文数 {
    public static String nearestPalindromic(String n) {
        int len = n.length();
        long cur = Long.parseLong(n);
        Set<Long> set = new HashSet<>();
        set.add((long)(Math.pow(10, len)) + 1);
        set.add((long)(Math.pow(10, len - 1)) - 1);
        long tem = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = tem - 1; i <= tem + 1; i++) {
            long t = buildValue(i, len % 2 == 0);
            if (t != cur) {
                set.add(t);
            }
        }
        long ans = -1;
        for (long i : set) {
            if (ans == -1) {
                ans = i;
            } else if (Math.abs(i - cur) < Math.abs(ans - cur)) {
                ans = i;
            } else if ((Math.abs(i - cur) == Math.abs(ans - cur)) && i < ans) {
                ans = i;
            }
        }
        return String.valueOf(ans);
    }
    private static long buildValue(long tem, boolean f) {
        StringBuilder sb = new StringBuilder();
        sb.append(tem);
        int n = sb.length(), idx = f ? n - 1 : n - 2;
        while (idx >= 0) {
            sb.append(sb.charAt(idx--));
        }
        return Long.parseLong(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(nearestPalindromic("2"));
    }
}
