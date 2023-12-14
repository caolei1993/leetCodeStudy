package main.java.com.exercise.week_054;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/14 20:14
 */
public class LeetCode_151_2_反转字符串中的单词 {
    public String reverseWords(String s) {
        s = s.trim();
        int j = s.length() - 1, i = j;
        StringBuilder bs = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            bs.append(s.substring(i + 1, j + 1)).append(" ");
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j = i;
        }
        return bs.toString().trim();
    }
}
