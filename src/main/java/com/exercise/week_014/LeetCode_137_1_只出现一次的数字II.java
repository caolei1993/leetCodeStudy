package main.java.com.exercise.week_014;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/4/30 17:21
 * @Version 1.0
 * https://leetcode-cn.com/problems/single-number-ii/
 */
public class LeetCode_137_1_只出现一次的数字II {
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (pre == nums[i]) {
                count++;
            } else {
                if (count == 3) {
                    count = 1;
                    pre = nums[i];
                } else {
                    return nums[i - 1];
                }
            }
        }
        return pre;
    }
}
