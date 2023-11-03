package main.java.com.exercise.week_050;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/3 19:02
 */
public class LeetCode_27_1_移除元素 {
    public int removeElement(int[] nums, int val) {
        boolean flag = false;
        Arrays.sort(nums);
        int len = nums.length;
        int ans = len;
        for (int i = 0; i < len;) {
            if (val == nums[i]) {
                ans--;
                flag = true;
                for (int j = i; j < len - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[len - 1] = -1;
            } else {
                if (flag) {
                    break;
                }
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,2,3};
        System.out.println(new LeetCode_27_1_移除元素().removeElement(nums, 3));
        System.out.println(Arrays.toString(nums));
    }
}
