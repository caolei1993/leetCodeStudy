package main.java.com.exercise.week_056;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/4 20:16
 */
public class LeetCode_392_2_判断子序列 {
    public boolean isSubsequence(String s, String t) {
        int i = s.length(), j = t.length();
        int m = 0 ,n = 0;
        while (m < i && n < j) {
            if (s.charAt(m) == t.charAt(n)) {
                m++;
            }
            n++;
        }
        return m == i;
    }
}
