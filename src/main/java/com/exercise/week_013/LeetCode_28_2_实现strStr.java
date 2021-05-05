package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/5/5 17:08
 * @Version 1.0
 */
public class LeetCode_28_2_实现strStr {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int n = haystack.length(), m = needle.length();
        char[] ch1 = haystack.toCharArray();
        char[] ch2 = needle.toCharArray();
        // 初始化next数组
        int[] next = new int[m];
        // j是前缀的最后一个元素，i是后缀的最后一个元素，最少要有两个元素，才能求最大相等前后缀的元素个数
        // 即后缀起始从1开始遍历，需要注意只有当j>0时，才需要找它之前的next的位置
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && ch2[j] != ch2[i]) {
                j = next[j - 1];
            }
            if (ch2[j] == ch2[i]) {
                j++;
            }
            next[i] = j;
        }
        // 从第一个元素开始匹配原数组和模板数组
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && ch1[i] != ch2[j]) {
                j = next[j - 1];
            }
            if (ch1[i] == ch2[j]) {
                j++;
            }
            // 需要注意首字母坐标为i - m + 1
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
