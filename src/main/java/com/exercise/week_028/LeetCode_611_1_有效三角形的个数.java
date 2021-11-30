package main.java.com.exercise.week_028;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/8/4 17:22
 * @Version 1.0
 */
public class LeetCode_611_1_有效三角形的个数 {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; j > k; j--) {
                while (k < j && nums[j] + nums[k] <= nums[i]) {
                    k++;
                }
                ans += j - k;
            }
        }
        return ans;
    }
}
