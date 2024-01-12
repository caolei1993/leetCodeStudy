package main.java.com.exercise.week_057;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/10 18:51
 */
public class LeetCode_209_1_长度最小的子数组 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < n; i++) {
            int j = n;
            while (j > i && pre[j] - pre[i] >= target) {
                ans = Math.min(j - i, ans);
                j--;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,4,3};
        System.out.println(new LeetCode_209_1_长度最小的子数组().minSubArrayLen(7, nums));
    }
}
