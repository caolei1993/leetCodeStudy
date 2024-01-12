package main.java.com.exercise.week_057;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/11 20:46
 */
public class LeetCode_209_2_长度最小的子数组 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = 0;
        while (r < n) {
            sum += nums[r];
            while (l <= r && sum >= target) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
