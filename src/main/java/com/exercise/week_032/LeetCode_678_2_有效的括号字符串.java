package main.java.com.exercise.week_032;

/**
 * @Author cl
 * @Date 2021/9/13 14:48
 * @Version 1.0
 */
public class LeetCode_678_2_有效的括号字符串 {
    public boolean checkValidString(String s) {
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
                r++;
            } else if (c == ')') {
                l--;
                r--;
            } else {
                l--;
                r++;
            }
            l = Math.max(l, 0);
            if (l > r) {
                return false;
            }
        }
        return l == 0;
    }
}
