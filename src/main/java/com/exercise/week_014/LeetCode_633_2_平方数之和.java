package main.java.com.exercise.week_014;

/**
 * @Author cl
 * @Date 2021/4/29 9:24
 * @Version 1.0
 */
public class LeetCode_633_2_平方数之和 {
    public boolean judgeSquareSum(int c) {
        int right = (int)Math.sqrt(c);
        int left = 0, value = 0;

        while (left <= right) {
            value = left * left + right * right;
            if (value == c) {
                return true;
            } else if (value < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
