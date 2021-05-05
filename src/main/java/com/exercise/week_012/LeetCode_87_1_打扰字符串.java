package main.java.com.exercise.week_012;

/**
 * @Author cl
 * @Date 2021/4/16 9:24
 * @Version 1.0
 */
public class LeetCode_87_1_打扰字符串 {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (!check(s1, s2)) {
            return false;
        }
        int length = s1.length();
        for (int i = 1; i < length; i++) {
            String s1Sub1 = s1.substring(0, i), s1Sub2 = s1.substring(i);
            String s2Sub1 = s2.substring(0, i), s2Sub2 = s2.substring(i);
            if (isScramble(s1Sub1, s2Sub1) && isScramble(s1Sub2, s2Sub2)) {
                return true;
            };
            String s2Sub3 = s2.substring(0, length - i), s2Sub4 = s2.substring(length - i);
            if (isScramble(s1Sub1, s2Sub3) && isScramble(s1Sub2, s2Sub4)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] a = new int[26], b = new int[26];
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        for (int i = 0; i < s1Arr.length; i++) {
            a[s1Arr[i] - 'a']++;
            b[s2Arr[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
