package main.java.com.exercise.week_014;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/4/29 16:10
 * @Version 1.0
 */
public class LeetCode_403_2_青蛙过河 {
    Map<Integer, Integer> map = new HashMap<>();
    Map<String, Boolean> cache = new HashMap<>();
    public boolean canCross(int[] stones) {
        int n = stones.length;
        for (int i = 0; i < n; i++) {
            map.put(stones[i], i);
        }
        // 判断第一步，因为第一步只可能跳1个单元格
        if (!map.containsKey(1)) {
            return false;
        }
        return dfs(stones, n,1, 1);
    }

    private boolean dfs(int[] stones, int n, int u, int k) {
        String key = u + "_" + k;
        // 如果已存在于缓存中，说明之前走过这一步
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (u == n - 1){
            return true;
        }

        for (int i = -1; i <= 1; i++) {
            // 原地踏步的不做处理
            if (k + i == 0) {
                continue;
            }
            int value = stones[u] + k + i;
            if (map.containsKey(value)) {
                boolean result =  dfs(stones, n, map.get(value), k + i);
                cache.put(key, result);
                if (result) {
                    return true;
                }
            }
        }
        cache.put(key, false);
        return false;
    }
}
