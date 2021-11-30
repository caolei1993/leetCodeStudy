package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/4 17:41
 * @Version 1.0
 */
public class LeetCode_367_1_有效的完全平方数 {
    public boolean isPerfectSquare(int num) {
        long l = 0, r = num;
        while (l < r) {
            long mid = l + r >> 1;
            if (mid * mid < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l * l == num;
    }
}
