package main.java.com.exercise.week_038;

/**
 * @Author cl
 * @Date 2021/10/21 9:14
 * @Version 1.0
 */
public class LeetCode_66_1_加一 {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] ans = new int[n];
        // 标记是否进位
        boolean flag = false;
        for (int i = n - 1; i >= 0 ; i--) {
            int value = digits[i];
            if (i == n - 1 || flag) {
                if (value == 9) {
                    value = 0;
                    flag = true;
                } else {
                    value += 1;
                    flag = false;
                }
            }
            ans[i] = value;
        }
        if (flag) {
            int[] re = new int[n + 1];
            System.arraycopy(ans, 0, re, 1, n);
            re[0] = 1;
            return re;
        }
        return ans;
    }
}
