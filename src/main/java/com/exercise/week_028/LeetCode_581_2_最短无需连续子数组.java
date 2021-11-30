package main.java.com.exercise.week_028;

/**
 * @Author cl
 * @Date 2021/8/3 11:22
 * @Version 1.0
 */
public class LeetCode_581_2_最短无需连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // 定义最小值，从后往前遍历，确认left边界
        int min = nums[n - 1], left = 0;
        // 定义最大值，从前往后遍历。确认right边界
        int max = nums[0], right = -1;

        for (int i = 0; i < n; i++) {
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }

            if (min < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right - left + 1;
    }
}
