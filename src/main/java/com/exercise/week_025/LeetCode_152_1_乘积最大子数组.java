package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/18 17:51
 * @Version 1.0
 */
public class LeetCode_152_1_乘积最大子数组 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // 状态定义，g[i]表示以nums[i]结尾的子数组的乘积最小值
        int[] g = new int[n];
        // 状态定义，f[i]表示以nums[i]结尾的子数组的乘积最大值
        int[] f = new int[n];
        f[0] = nums[0];
        g[0] = nums[0];
        int ans = f[0];
        for (int i = 1; i < n; i++) {
            g[i] = Math.min(nums[i], Math.min(g[i - 1] * nums[i], f[i - 1] * nums[i]));
            f[i] = Math.max(nums[i], Math.max(g[i - 1] * nums[i], f[i - 1] * nums[i]));
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
