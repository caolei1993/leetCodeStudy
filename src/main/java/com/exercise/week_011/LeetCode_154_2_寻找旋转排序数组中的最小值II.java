package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/9 10:30
 * @Version 1.0
 */
public class LeetCode_154_2_寻找旋转排序数组中的最小值II {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] > nums[r]){
                l = mid + 1;
            } else {
                r -= 1;
            }
        }
        return nums[l];
    }
}
