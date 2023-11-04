package main.java.com.exercise.week_050;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/4 14:06
 */
public class LeetCode_80_1_删除有序数组中的重复项II {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int count = 1;
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (nums[index] == nums[i]) {
                count++;
                if (count <= 2) {
                    nums[++index] = nums[i];
                }
            } else {
                count = 1;
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
}
