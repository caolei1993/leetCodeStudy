package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/19 14:19
 * @Version 1.0
 */
public class LeetCode_27_2_移除元素 {
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int left = 0, right = length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
