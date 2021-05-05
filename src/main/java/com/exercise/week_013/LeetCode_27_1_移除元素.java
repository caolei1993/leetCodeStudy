package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/19 9:09
 * @Version 1.0
 * https://leetcode-cn.com/problems/remove-element/
 */
public class LeetCode_27_1_移除元素 {
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int slow = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] != val) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        return slow;
    }
}
