package main.java.com.exercise.week_052;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/27 20:41
 */
public class LeetCode_274_2_H指数 {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] nums = new int[len + 1];
        for (int c : citations) {
            nums[Math.min(c, len)]++;
        }
        int total = 0;
        for (int i = len; i >= 0; i--) {
            total += nums[len];
            if (total >= len) {
                return total;
            }
        }
        return -1;
    }
}
