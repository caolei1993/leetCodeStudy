package main.java.com.exercise.week_049;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/25 4:31 下午
 */
public class LeetCode_537_1_复数乘法 {
    public String complexNumberMultiply(String num1, String num2) {
        String[] s1 = num1.split("\\+|i"), s2 = num2.split("\\+|i");
        int a = Integer.parseInt(s1[0]);
        int b = Integer.parseInt(s1[1]);
        int c = Integer.parseInt(s2[0]);
        int d = Integer.parseInt(s2[1]);
        int x = a * c - b * d;
        int y = a * d + b * c;
        return x + "+" + y +"i";
    }
}
