package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/6 21:24
 * @Version 1.0
 */
public class LeetCode_268_2_丢失的数字 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        boolean[] flag = new boolean[n + 1];
        for (int num : nums) {
            flag[num] = true;
        }
        for (int i = 0; i <= n; i++) {
            if (!flag[i]) {
                return i;
            }
        }
        return -1;
    }
}
