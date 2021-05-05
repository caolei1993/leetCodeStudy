package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/8 17:36
 * @Version 1.0
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class LeetCode_153_1_寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int l = 0, r = n;
        // 搜索区间为[l,r)
        while (l < r) {
            int mid =  l + r >> 1;
            if (nums[mid] == nums[0]) {
                l = mid + 1;
            } else if (nums[mid] > nums[0]){
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l == n ? nums[0] : nums[l];
    }
}
