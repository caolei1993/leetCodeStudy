package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/20 11:19
 * @Version 1.0
 * https://leetcode-cn.com/problems/implement-strstr/
 */
public class LeetCode_28_1_实现strStr {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int n = haystack.length(), m = needle.length();
        char[] ch1 = haystack.toCharArray();
        char[] ch2 = needle.toCharArray();

        for (int i = 0; i < n - m; i++) {
            // 记录haystack遍历的下标a,以及needle的遍历下标b
            int a = i, b = 0;
            while (b < m && ch1[a] == ch2[b]) {
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
