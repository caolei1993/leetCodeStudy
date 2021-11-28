package main.java.com.exercise.week_041;

import java.util.Arrays;

public class LeetCode_423_1_从英文中重建数字 {
    static String[] ss = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static int[] priority = new int[]{0, 8, 6, 3, 2, 7, 5, 9, 4, 1};

    public String originalDigits(String s) {
        int n = s.length();
        int[] cnts = new int[26];
        // 统计字符串中各个字母的词频
        for (int i = 0; i < n; i++) {
            cnts[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int v : priority) {
            int k = Integer.MAX_VALUE;

            char[] cc = ss[v].toCharArray();
            for (char c : cc) {
                k = Math.min(k, cnts[c - 'a']);
            }
            for (char c : cc) {
                cnts[c - 'a'] -= k;
            }
            while (k-- > 0) {
                sb.append(v);
            }
        }
        char[] chArr = sb.toString().toCharArray();
        Arrays.sort(chArr);
        return String.valueOf(chArr);
    }
}
