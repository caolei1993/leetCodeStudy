package main.java.com.exercise.week_040;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/11/6 21:17
 * @Version 1.0
 */
public class LeetCode_268_1_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n ; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
}
