package main.java.com.exercise.week_044;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/22 9:35 上午
 */
public class LeetCode_686_1_重复叠加字符串匹配 {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length()) {
            sb.append(a);
            ans++;
        }
        sb.append(a);
        int idx = sb.indexOf(b);
        if (idx == -1) {
            return -1;
        }
        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }
}
