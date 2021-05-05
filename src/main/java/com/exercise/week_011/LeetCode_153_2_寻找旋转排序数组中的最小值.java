package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/9 9:22
 * @Version 1.0
 */
public class LeetCode_153_2_寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 搜索区间为[l, n], l=n未处理
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 不存在nums[mid] = nums[r]的情况，除非数组只有一个元素
            // 一个元素的数组，不会进入while循环
            if (nums[mid] < nums[r]) {
                r = mid;
            // nums[mid] > nums[r]
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
