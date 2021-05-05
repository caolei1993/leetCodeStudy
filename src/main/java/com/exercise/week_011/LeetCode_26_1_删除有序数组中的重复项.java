package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/6 21:21
 * @Version 1.0
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class LeetCode_26_1_删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return length;
        }
        int slow = 1, fast = 1;
        for (int i = 1; i < length; i++) {
            if (nums[slow - 1] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
