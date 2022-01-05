package main.java.com.exercise.week_046;

/**
 * @author cl
 * @version 1.0
 * @date 2022/1/5 12:48 下午
 */
public class LeetCode_1576_1_替换所有的问号 {
    public String modifyString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3 && cs[i] == '?'; j++) {
                boolean flag = true;
                if (i - 1 >= 0 && cs[i - 1] == 'a' + j) {
                    flag = false;
                }
                if (i + 1 < n && cs[i + 1] == 'a' + j) {
                    flag = false;
                }
                if (flag) {
                    cs[i] = (char)('a' + j);
                }
            }
        }
        return String.valueOf(cs);
    }
}
