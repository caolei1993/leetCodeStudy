package main.java.com.exercise.week_033;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/9/13 9:28
 * @Version 1.0
 */
public class LeetCode_447_1_回旋镖的数量 {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int x = points[i][0] - points[j][0], y = points[i][1] - points[j][1];
                int value = x * x + y * y;
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            for (Integer key : map.keySet()) {
                int cnt = map.get(key);
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }
}
