package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/18 17:25
 * @Version 1.0
 */
public class LeetCode_剑指Offer42_2_连续子数组的最大和 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int ans = nums[0], max = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(nums[i], max + nums[i]);
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
