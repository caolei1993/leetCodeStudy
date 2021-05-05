package main.java.com.exercise.week_012;

/**
 * @Author cl
 * @Date 2021/4/19 11:23
 * @Version 1.0
 * https://leetcode-cn.com/problems/house-robber/
 */
public class LeetCode_198_1_打家劫舍 {
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
        int[] rob = new int[length];
        rob[0] = nums[0];
        rob[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            rob[i] = Math.max(rob[i - 2] + nums[i], rob[i - 1]);
        }
        return rob[length - 1];
    }
}
