package main.java.com.exercise.week_033;

/**
 * @Author cl
 * @Date 2021/9/18 17:33
 * @Version 1.0
 */
public class LeetCode_162_2_寻找峰值 {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] > nums[mid - 1]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
