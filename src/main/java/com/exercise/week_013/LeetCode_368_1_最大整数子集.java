package main.java.com.exercise.week_013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author cl
 * @Date 2021/4/23 9:50
 * @Version 1.0
 * https://leetcode-cn.com/problems/largest-divisible-subset/
 */
public class LeetCode_368_1_最大整数子集 {
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        // f[i]代表i之前所有数据的最大整数子集个数
        int[] f = new int[length];
        // g[i]代表f[i]代表的整数子集，前一个元素的下标
        int[] g = new int[length];
        // 遍历所有数据
        for (int i = 0; i < length; i++) {
            // 初始化子集长度为1，回溯坐标为i
            int max = 1, pre = i;
            // 查询i以前的所有数据，看是否存在符合整数子集规则的
            for (int j = 0; j < i ; j++) {
                boolean result = nums[i] % nums[j] == 0;
                if (result) {
                    int count = f[j] + 1;
                    if (count > max) {
                        max = count;
                        pre = j;
                    }
                }
            }
            f[i] = max;
            g[i] = pre;
        }
        int size = -1, idx = -1;
        for (int i = 0; i < length; i++) {
            if (f[i] > size) {
                size = f[i];
                idx = i;
            }
        }
        List<Integer> list = new ArrayList<>(size);
        while (list.size() != size) {
            list.add(nums[idx]);
            idx = g[idx];
        }
        return list;
    }

}
