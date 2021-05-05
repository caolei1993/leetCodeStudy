package main.java.com.exercise.week_012;

/**
 * @Author cl
 * @Date 2021/4/19 12:22
 * @Version 1.0
 */
public class LeetCode_198_2_打家劫舍 {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 用变量代表i-2位置的rob的值
        int first = nums[0];
        // 代表i-1位置的rob的值
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int tem = second;
            second = Math.max(first + nums[i], second);
            first = tem;
        }
        return second;
    }
}
