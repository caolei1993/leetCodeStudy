package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/7 19:22
 * @Version 1.0
 */
public class LeetCode_268_3_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int cur = 0, sum;
        sum = n * (n + 1) / 2;
        for (int num : nums) {
            cur += num;
        }
        return sum - cur;
    }
}
