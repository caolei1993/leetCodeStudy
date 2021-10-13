package main.java.com.exercise.week_037;

/**
 * @Author cl
 * @Date 2021/10/11 19:53
 * @Version 1.0
 */
public class LeetCode_273_1_整数转化英文表示 {
    static final String[] SMALL = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static final String[] MEDIUM = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static final String[] LARGE = { "Billion", "Million", "Thousand", ""};

    private String numToStr(int x) {
        StringBuilder sb = new StringBuilder();
        if (x >= 100) {
            sb.append(SMALL[x / 100]).append(" Hundred ");
            x %= 100;
        }
        if (x >= 20) {
            sb.append(MEDIUM[x / 10]).append(" ");
            x %= 10;
        }
        if (x != 0) {
            sb.append(SMALL[x]).append(" ");
        }
        return sb.toString();
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return SMALL[num];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = (int)1e9, j = 0; i > 0 ; i /= 1000, j++) {
            if (num < i) {
                continue;
            }
            sb.append(numToStr(num / i)).append(LARGE[j]).append(" ");
            num %= i;
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
