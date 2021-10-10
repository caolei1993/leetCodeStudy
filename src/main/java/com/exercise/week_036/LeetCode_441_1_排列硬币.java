package main.java.com.exercise.week_036;

/**
 * @Author cl
 * @Date 2021/10/10 11:36
 * @Version 1.0
 */
public class LeetCode_441_1_排列硬币 {
    public int arrangeCoins(int n) {
        return (int)((Math.sqrt(1 + 8.0 * n) - 1) / 2);
    }
}
