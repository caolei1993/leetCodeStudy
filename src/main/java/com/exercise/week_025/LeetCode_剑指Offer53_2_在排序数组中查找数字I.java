package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/19 16:17
 * @Version 1.0
 */
public class LeetCode_剑指Offer53_2_在排序数组中查找数字I {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[l] != target) {
            return 0;
        }
        // target的左边界
        int a = l;
        l = 0;
        r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // target的右边界
        int b = l;

        return  b - a + 1;
    }
}
