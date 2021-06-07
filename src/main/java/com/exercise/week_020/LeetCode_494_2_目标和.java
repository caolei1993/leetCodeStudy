package main.java.com.exercise.week_020;

/**
 * @Author cl
 * @Date 2021/6/7 22:04
 * @Version 1.0
 */
public class LeetCode_494_2_目标和 {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        // 初始化并计算所有数字的总和
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        // 目标值比所有数字总和都大或者比所有数字总和的负数都小，直接返回0
        if (target > s || target < -s) {
            return 0;
        }
        // 定义f[i][j],代表前i个数字，求取和为j的方案数，j的取值范围为[-s,s]，f[0][0]的值为1，为了避免j为负值，所有值右移s
        int[][] f = new int[n + 1][2 * s + 1];
        f[0][0 + s] = 1;
        for (int i = 1; i <= n ; i++) {
            int x = nums[i - 1];
            for (int j = -s; j <= s; j++) {
                if ((j - x) + s >= 0) {
                    f[i][j + s] += f[i - 1][(j - x) + s];
                }
                if ((j + x) + s <= 2 * s) {
                    f[i][j + s] += f[i - 1][(j + x) + s];
                }
            }
        }
        return f[n][target + s];
    }
}
