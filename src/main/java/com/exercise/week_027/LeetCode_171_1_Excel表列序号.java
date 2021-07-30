package main.java.com.exercise.week_027;

/**
 * @Author cl
 * @Date 2021/7/30 10:22
 * @Version 1.0
 */
public class LeetCode_171_1_Excel表列序号 {
    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int sum = 0;
        int factor = 26;
        for (char c :  chars) {
            int v = c - 'A' + 1;
            sum = sum * 26 + v;
        }
        return sum;
    }
}
