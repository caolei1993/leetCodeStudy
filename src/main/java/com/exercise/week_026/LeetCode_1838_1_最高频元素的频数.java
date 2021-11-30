package main.java.com.exercise.week_026;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/7/20 16:07
 * @Version 1.0
 */
public class LeetCode_1838_1_最高频元素的频数 {
    int[] nums, sums;
    int n, k;
    public int maxFrequency(int[] nums1, int k1) {
        nums = nums1;
        k = k1;
        n = nums.length;
        // 对数组进行排序，保证前缀和的单调性
        Arrays.sort(nums);
        // 定义前缀和数组，并初始化前缀和数组
        sums = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int len) {
        for (int l = 0; l + len - 1 < n; l++) {
            int r = l + len - 1;
            int a = sums[r + 1] - sums[l];
            int b = nums[r] * len;
            if (b - a <= k) {
                return true;
            }
        }
        return false;
    }
}
