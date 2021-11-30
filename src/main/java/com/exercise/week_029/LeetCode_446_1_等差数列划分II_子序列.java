package main.java.com.exercise.week_029;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/8/11 15:01
 * @Version 1.0
 */
public class LeetCode_446_1_等差数列划分II_子序列 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        // maps[i] 代表以nums[i]为尾项的所有等差子序列的集合，map的k，v分别代表差值和子序列数量
        Map<Long, Integer>[] maps = new Map[n];
        // 初始化数组中的所有map对象
        for (int i = 0; i < n; i++) {
            maps[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long d = 1L * nums[i] - nums[j];
                int cnts = maps[j].getOrDefault(d, 0);
                ans += cnts;
                maps[i].put(d, maps[i].getOrDefault(d, 0) + cnts + 1);
            }
        }
        return ans;
    }
}
