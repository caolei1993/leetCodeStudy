package main.java.com.exercise.week_001;

import java.text.DecimalFormat;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/29 10:55 AM
 */
public class Test_8 {

    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : words) {
            if (s.startsWith("$") && s.length() > 1 && s.substring(1).matches("\\d+")) {
                double d = Double.parseDouble(s.substring(1));
                d = d - d * discount / 100;
                DecimalFormat format = new DecimalFormat("0.00");
                sb.append("$").append(format.format(d)).append(" ");
            } else {
                sb.append(s).append(" ");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        String s = "4999999999999999999";
        System.out.println(s.length());
        Long.parseLong(s);
    }
}
