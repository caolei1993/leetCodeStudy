package main.java.com.exercise.week_006;

/**
 * @Author cl
 * @Date 2021/2/15 22:14
 * @Version 1.0
 */
public class LeetCode_856_3_括号的分数 {
    public int scoreOfParentheses(String S) {
        int ans = 0, depth = 0;
        for(int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                if (S.charAt(i - 1) == '(') {
                    ans += 1 << depth;
                }
            }
        }
        return ans;
    }
}
