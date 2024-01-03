package main.java.com.exercise.week_056;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/3 18:24
 */
public class LeetCode_125_1_验证回文串 {
    public boolean isPalindrome(String s) {
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String str = sb.toString().toLowerCase();
        String revers = sb.reverse().toString().toLowerCase();
        if (str.equals(revers)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(new LeetCode_125_1_验证回文串().isPalindrome(s));
    }
}
