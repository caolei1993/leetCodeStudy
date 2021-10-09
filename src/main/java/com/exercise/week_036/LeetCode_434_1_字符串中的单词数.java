package main.java.com.exercise.week_036;

/**
 * @Author cl
 * @Date 2021/10/7 10:01
 * @Version 1.0
 */
public class LeetCode_434_1_字符串中的单词数 {
    public int countSegments(String s) {
        int ans = 0;
        String[] ss = s.split(" ");
        for (int i = 0; i < ss.length; i++) {
            if (!ss[i].equals("")) {
                ans++;
            }
        }
        return ans;
    }

}
