package main.java.com.exercise.week_040;

import java.util.Arrays;

public class LeetCode_594_1_最长和谐子序列 {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            while (nums[r] - nums[l] > 1) {
                l++;
            }
            if (nums[r] - nums[l] == 1) {
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }
}