package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/16 20:52
 * @Version 1.0
 */
public class LeetCode_剑指Offer53_1_在排序数组中查找数字I {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int count = 0;
        while (l <= r) {
            int mid = l + r >> 1;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                count++;
                int c = mid;
                while (nums[--c] == target) {
                    count++;
                }
                while (nums[++mid] == target) {
                    count++;
                }
                return count;
            }
        }
        return count;
    }
}
