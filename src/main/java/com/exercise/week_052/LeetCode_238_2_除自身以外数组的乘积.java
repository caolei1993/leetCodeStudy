package main.java.com.exercise.week_052;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/30 20:30
 */
public class LeetCode_238_2_除自身以外数组的乘积 {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        left[0]  = 1;
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            left[i] = left[i] * right;
            right *= nums[i];
        }
        return left;
    }
}
