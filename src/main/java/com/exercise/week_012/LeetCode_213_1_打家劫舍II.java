package main.java.com.exercise.week_012;

/**
 * @Author cl
 * @Date 2021/4/15 9:16
 * @Version 1.0
 * https://leetcode-cn.com/problems/house-robber-ii/
 */
public class LeetCode_213_1_打家劫舍II {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
          return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        return Math.max(maxRob(nums, 0 , length - 2), maxRob(nums, 1, length - 1));
    }

    private int maxRob(int[] nums, int start, int end) {
        // 保存i-2位置的抢劫最大值
        int first = nums[start];
        // 保存i-1位置的抢劫最大值
        int secend = Math.max(first, nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int tem = secend;
            secend = Math.max(first + nums[i], secend);
            first = tem;
        }
        return secend;
    }

}
