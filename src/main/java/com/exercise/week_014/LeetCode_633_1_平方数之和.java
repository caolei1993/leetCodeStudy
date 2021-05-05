package main.java.com.exercise.week_014;

/**
 * @Author cl
 * @Date 2021/4/28 9:15
 * @Version 1.0
 */
public class LeetCode_633_1_平方数之和 {
    public boolean judgeSquareSum(int c) {
        int max = (int)Math.sqrt(c);
        for (int a = 0; a <= max ; a++) {
            int b = (int)Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }
}
