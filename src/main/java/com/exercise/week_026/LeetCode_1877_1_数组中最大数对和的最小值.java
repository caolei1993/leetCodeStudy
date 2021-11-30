package main.java.com.exercise.week_026;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/7/20 14:39
 * @Version 1.0
 */
public class LeetCode_1877_1_数组中最大数对和的最小值 {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n >> 1 ; i++) {
            int max = nums[i] + nums[n - 1 - i];
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
