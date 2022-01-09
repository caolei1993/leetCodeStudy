package main.java.com.exercise.week_046;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/8 5:09 下午
 */
public class LeetCode_1614_1_括号的最大嵌套深度 {
    public int maxDepth(String s) {
        char[] cs = s.toCharArray();
        int ans = 0, val = 0;
        for (char c : cs) {
            if (c == '(') {
                val++;
            }
            if (c == ')') {
                val--;
            }
            ans = Math.max(ans, val);
        }
        return ans;
    }
}
