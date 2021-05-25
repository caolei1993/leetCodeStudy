package main.java.com.exercise.week_018;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/25 20:57
 */
public class LeetCode_1787_1_使所有区间的异或结果为零 {
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int max = 1024;
        // f[i][j]代表前i+1列，首行异或结果为j的最小修改次数
        int[][] f = new int[k][max];
        // 记录每列最小修改次数的值
        int[] g = new int[k];
        Arrays.fill(g, Integer.MAX_VALUE);

        // 遍历每列, i为列数，cnt记录每列总共有多少个数字
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 记录每列中每个值的个数
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            // 首列，只需要考虑修改几个得到xor值即可
            if (i == 0) {
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = cnt - map.getOrDefault(xor, 0);
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else {
                // 非首列，需要考虑前一列
                for (int xor = 0; xor < max; xor++) {
                    // 整列修改
                    f[i][xor] = g[i - 1] + cnt;
                    // 非整列修改
                    for (int num: map.keySet()) {
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ num] + cnt - map.get(num));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }
}

