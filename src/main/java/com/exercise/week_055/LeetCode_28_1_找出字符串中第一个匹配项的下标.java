package main.java.com.exercise.week_055;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/20 21:52
 */
public class LeetCode_28_1_找出字符串中第一个匹配项的下标 {
    public int strStr(String haystack, String needle) {
        char[] ss = haystack.toCharArray(), cc = needle.toCharArray();
        int m = ss.length, n = cc.length;
        for (int i = 0; i < m - n; i++) {
            int a = i, b = 0;
            while (b < m && ss[a] == cc[b]) {
                a++;
                b++;
            }
            if (b == m) {
                return i;
            }
        }
        return -1;
    }
}
