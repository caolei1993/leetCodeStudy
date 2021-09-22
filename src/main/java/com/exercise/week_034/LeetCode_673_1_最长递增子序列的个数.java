package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/20 20:37
 * @Version 1.0
 */
public class LeetCode_673_1_最长递增子序列的个数 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n], g = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            f[i] = g[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if (f[i] == f[j] + 1) {
                        g[i] += g[j];
                    }
                }
            }
            max = Math.max(max, f[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] == max) {
                ans += g[i];
            }
        }
        return ans;
    }
}
