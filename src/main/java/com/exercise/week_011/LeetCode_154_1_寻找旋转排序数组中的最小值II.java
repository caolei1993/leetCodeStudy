package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/9 9:50
 * @Version 1.0
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class LeetCode_154_1_寻找旋转排序数组中的最小值II {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 恢复数组两段性
        while (l < r && nums[0] == nums[r]) {
            r--;
        }

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
