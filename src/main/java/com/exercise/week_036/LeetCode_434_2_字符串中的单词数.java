package main.java.com.exercise.week_036;

/**
 * @Author cl
 * @Date 2021/10/7 10:13
 * @Version 1.0
 */
public class LeetCode_434_2_字符串中的单词数 {
    public int countSegments(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n;) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }
            while (i < n && s.charAt(i) != ' ') {
                i++;
            }
            ans++;
        }
        return ans;
    }
}
