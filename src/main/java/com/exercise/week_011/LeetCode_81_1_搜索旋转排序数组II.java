package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/7 10:35
 * @Version 1.0
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 */
public class LeetCode_81_1_搜索旋转排序数组II {
    public boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }
}
