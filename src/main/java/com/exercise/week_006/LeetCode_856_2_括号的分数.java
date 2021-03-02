package main.java.com.exercise.week_006;

/**
 * @Author cl
 * @Date 2021/2/15 21:41
 * @Version 1.0
 * 分治法
 */
public class LeetCode_856_2_括号的分数 {
    public int scoreOfParentheses(String S) {
      return scoreOfSubStr(S, 0, S.length()-1);
    }

    public int scoreOfSubStr(String str, int start, int end) {
        int ans = 0;
        int sum = 0;
        int index = start;
        for (int i= start; i <= end; i++) {
            int value = str.charAt(i) == '(' ? 1 : -1;
            sum += value;
            if (sum == 0) {
                if (i - index == 1) {
                    ans++;
                } else {
                    ans = ans + 2 * scoreOfSubStr(str, index+1, i-1);
                }
                index = i + 1;
            }
        }
        return ans;
    }
}
