package main.java.com.exercise.week_019;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/6/3 21:14
 * @Version 1.0
 */
public class LeetCode_525_1_连续数组 {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        // 定义前缀和数组，并初始化，把0当做-1处理
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        }
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i <= n ; i++) {
            if (!map.containsKey(sum[i - 2])) {
                map.put(sum[i - 2], i - 2);
            }
            if (map.containsKey(sum[i])) {
                ans = Math.max(ans, i - map.get(sum[i]));
            }
        }
        return ans;
    }
}
