package main.java.com.exercise.week_048;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/1 10:30 下午
 */
public class LeetCode_1763_2_最长的美好子字符串 {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int idx = -1, len = 0;
        for (int i = 0; i < n; i++) {
            int a = 0, b = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    a |= 1 << (c - 'a');
                } else {
                    b |= 1 << (c - 'A');
                }
                if (j - i + 1 > len && a == b) {
                    idx = i;
                    len = j - i + 1;
                }
            }
        }
        return idx == -1 ? "" : s.substring(idx , idx + len);
    }
}
