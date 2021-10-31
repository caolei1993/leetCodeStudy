package main.java.com.exercise.week_039;

/**
 * @Author cl
 * @Date 2021/10/30 19:50
 * @Version 1.0
 */
public class LeetCode_260_1_只出现一次的数字III {
    public int[] singleNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int index = Integer.MAX_VALUE;
        for (int i = 31; i >= 0 ; i--) {
            if (((sum >> i) & 1) == 1) {
                index = i;
                break;
            }
        }
        int[] ans = new int[2];
        for (int num : nums) {
            if (((num >> index) & 1) == 1) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        return ans;
    }
}
