package main.java.com.exercise.week_050;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/3 19:19
 */
public class LeetCode_27_2_移除元素 {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int index = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
