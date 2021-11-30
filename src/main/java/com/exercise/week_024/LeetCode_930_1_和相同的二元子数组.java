package main.java.com.exercise.week_024;

/**
 * @Author cl
 * @Date 2021/7/8 21:31
 * @Version 1.0
 */
public class LeetCode_930_1_和相同的二元子数组 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int ans = 0;
        int n = nums.length;
        int[] sums = new int[n + 1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += nums[i - 1];
            sums[i] = sum;
        }

        for (int i = 0; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                int value = sums[j] - sums[i];
                if (value > goal) {
                    break;
                }
                if (value == goal) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
