package main.java.com.exercise.week_035;

/**
 * @Author cl
 * @Date 2021/10/2 13:48
 * @Version 1.0
 */
public class LeetCode_405_1_数字转化为十六进制数 {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        long n = num;
        StringBuilder sb = new StringBuilder();
        if (n < 0) {
            n = (long)(Math.pow(2, 32) + n);
        }
        while (n != 0) {
            int a = num % 16;
            if (a == 0) {
                a = num;
            }
            if (a > 9) {
                sb.append((char)((a - 10) + 'a'));
            } else {
                sb.append(a);
            }
            num /= 16;
        }

        return sb.reverse().toString();
    }
}
