package main.java.com.exercise.week_048;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/2 5:37 下午
 */
public class LeetCode_2000_1_反转单词前缀 {
    public String reversePrefix(String word, char ch) {
        char[] chs = word.toCharArray();
        int n = chs.length, idx = -1;
        for (int i = 0; i < n && idx == -1; i++) {
            if (chs[i] == ch) {
                idx = i;
            }
        }
        int l = 0, r = idx;
        while (l < r) {
            char c = chs[l];
            chs[l++] = chs[r];
            chs[r--] = c;
        }
        return String.valueOf(chs);
    }
}
