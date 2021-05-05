package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/6 17:59
 * @Version 1.0
 */
public class LeetCode_80_2_删除有序数组中的重复项II {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return length;
        }
        int slow = 2, fast = 2;
        while (fast < length) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
