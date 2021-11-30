package main.java.com.exercise.week_028;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/8/3 9:46
 * @Version 1.0
 */
public class LeetCode_581_1_最短无序连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int left = 0, right = n - 1;
        while (left < n && arr[left] == nums[left]) {
            left++;
        }
        if (left == n) {
            return 0;
        }
        while (arr[right] == nums[right]) {
            right--;
        }

        return right - left + 1;
    }
}
