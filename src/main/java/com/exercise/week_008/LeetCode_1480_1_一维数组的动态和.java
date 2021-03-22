package main.java.com.exercise.week_008;

/**
 * @Author cl
 * @Date 2021/3/10 21:20
 * @Version 1.0
 * https://leetcode-cn.com/problems/running-sum-of-1d-array/
 */
public class LeetCode_1480_1_一维数组的动态和 {
    public int[] runningSum(int[] nums) {
        int[] ans =  new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans[i] = sum;
        }
        return ans;
    }
}
