package main.java.com.exercise.week_037;

/**
 * @Author cl
 * @Date 2021/10/15 9:14
 * @Version 1.0
 */
public class LeetCode_38_1_外观数列 {
    public String countAndSay(int n) {
        String ans = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder cur = new StringBuilder();
            int m = ans.length();
            for (int j = 0; j < m;) {
                int k = j + 1;
                while (k < m && ans.charAt(j) == ans.charAt(k)) {
                    k++;
                }
                cur.append(k - j).append(ans.charAt(j));
                j = k;
            }
            ans = cur.toString();
        }
        return ans;
    }
}
