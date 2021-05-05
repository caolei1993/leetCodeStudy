package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/7 13:43
 * @Version 1.0
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class LeetCode_33_1_搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = length - 1;

        while (l < r) {
            int mid = (l + r + 1) >> 1;
            // 寻找分割点（前段的最后一个值的index）
            if (nums[mid] > nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        if (target >= nums[0]) {
            l = 0;
        } else {
            l = l + 1;
            r = length - 1;
        }

        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[r] == target ? l : -1;
    }
}
