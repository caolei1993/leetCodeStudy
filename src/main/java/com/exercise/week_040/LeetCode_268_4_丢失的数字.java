package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/7 19:26
 * @Version 1.0
 */
public class LeetCode_268_4_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int val = 0;
        for (int i = 0; i <= n; i++) {
            val ^= i;
        }
        for (int num : nums) {
            val ^= num;
        }
        return val;
    }
}
