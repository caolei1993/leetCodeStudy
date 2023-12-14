package main.java.com.exercise.week_054;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/14 19:38
 */
public class LeetCode_151_1_反转字符串中的单词 {
    public String reverseWords(String s) {
        String[] ss = s.split(" ");
        int len  = ss.length;
        StringBuilder ans = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            if (ss[i].trim().length() != 0) {
                ans.append(ss[i]).append(" ");
            }
        }
        return ans.toString().trim();
    }
}
