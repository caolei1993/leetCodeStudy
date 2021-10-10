package main.java.com.exercise.week_036;

/**
 * @Author cl
 * @Date 2021/10/10 12:00
 * @Version 1.0
 */
public class LeetCode_441_2_排列硬币 {
    public int arrangeCoins(int n) {
        long l = 1, r = n;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if ((mid * (mid + 1)) / 2 <= n) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (int)r;
    }
}
