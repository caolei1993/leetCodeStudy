package main.java.com.exercise.week_032;

/**
 * @Author cl
 * @Date 2021/9/6 16:34
 * @Version 1.0
 */
public class LeetCode_704_1_二分查找 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[r] == target ? r : -1;
    }
}
