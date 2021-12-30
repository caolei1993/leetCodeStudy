package main.java.com.exercise.week_045;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/29 5:05 下午
 */
public class LeetCode_1995_3_统计特殊四元组 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] cnt = new int[1000];
        // 变式子: nums[a] + nums[b] = nums[d] - nums[c]
        for (int b = n - 3; b >= 1; b--) {
            for (int d = b + 2; d < n; d++) {
                // 减法的值有可能为负数，整体偏移200
                cnt[nums[d] - nums[b + 1] + 200]++;
            }
            for (int a = 0; a < b; a++) {
                ans += cnt[nums[a] + nums[b] + 200];
            }
        }
        return ans;
    }
}
