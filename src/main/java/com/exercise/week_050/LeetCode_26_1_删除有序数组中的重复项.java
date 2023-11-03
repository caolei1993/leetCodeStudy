package main.java.com.exercise.week_050;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/3 19:46
 */
public class LeetCode_26_1_删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(new LeetCode_26_1_删除有序数组中的重复项().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
