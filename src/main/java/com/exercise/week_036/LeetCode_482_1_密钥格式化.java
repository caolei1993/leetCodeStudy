package main.java.com.exercise.week_036;

/**
 * @Author cl
 * @Date 2021/10/4 19:56
 * @Version 1.0
 */
public class LeetCode_482_1_密钥格式化 {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            if (cnt == k) {
                sb.append("-");
                cnt = 0;
            }
            sb.append(c);
            cnt++;
        }
        return sb.reverse().toString().toUpperCase();
    }
}