package main.java.com.exercise.week_038;

/**
 * @Author cl
 * @Date 2021/10/20 19:43
 * @Version 1.0
 */
public class LeetCode_453_1_最小操作次数使数组元素相等 {
    public int minMoves(int[] nums) {
        int n = nums.length;
        long sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        return (int)(sum - min * n);
    }
}
