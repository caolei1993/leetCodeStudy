package main.java.com.exercise.week_019;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/6/2 21:01
 * @Version 1.0
 */
public class LeetCode_523_1_连续的子数组和 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // 创建并初始化前缀和数组
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }
}
