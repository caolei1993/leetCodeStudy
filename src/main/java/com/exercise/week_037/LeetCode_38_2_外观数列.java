package main.java.com.exercise.week_037;

/**
 * @Author cl
 * @Date 2021/10/15 9:27
 * @Version 1.0
 */
public class LeetCode_38_2_外观数列 {
    static String[] f = new String[35];
    static {
        f[1] = "1";
        for (int i = 2; i <= 35; i++) {
            String pre = f[i - 1];
            StringBuilder cur = new StringBuilder();
            int m = pre.length();
            for (int j = 0; j < m;) {
                int k = j + 1;
                while (k < m && pre.charAt(k) == pre.charAt(j)) {
                    k++;
                }
                cur.append(k - j).append(pre.charAt(j));
                j = k;
            }
            f[i] = cur.toString();
        }
    }

    public String countAndSay(int n) {
        return f[n];
    }
}
