package main.java.com.exercise.week_035;

/**
 * @Author cl
 * @Date 2021/10/6 22:08
 * @Version 1.0
 */
public class LeetCode_405_2_数字转化为十六进制数 {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int a = num & 15;
            char c = (char)(a + '0');
            if (a > 9) {
               c = (char)((a - 10) + 'a');
            }

            sb.append(c);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
}
