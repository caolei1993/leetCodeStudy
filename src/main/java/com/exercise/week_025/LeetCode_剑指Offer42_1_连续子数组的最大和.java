package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/18 17:00
 * @Version 1.0
 */
public class LeetCode_剑指Offer42_1_连续子数组的最大和 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 状态定义，f[i]代表以nums[i]为结尾的子数组的最大值
        int[] f = new int[n];
        f[0] = nums[0];
        int ans = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(nums[i], f[i - 1] + nums[i]);
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
