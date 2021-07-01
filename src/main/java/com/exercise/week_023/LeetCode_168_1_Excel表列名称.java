package main.java.com.exercise.week_023;

/**
 * @Author cl
 * @Date 2021/6/29 21:25
 * @Version 1.0
 */
public class LeetCode_168_1_Excel表列名称 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char)((columnNumber % 26) + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }
}
