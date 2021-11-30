package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/4 17:47
 * @Version 1.0
 */
public class LeetCode_367_2_有效的完全平方数 {
    public boolean isPerfectSquare(int num) {
        int x = 1;
        while (num > 0) {
            num -= x;
            x += 2;
        }
        return num == 0;
    }
}
