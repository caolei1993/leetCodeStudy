package main.java.com.exercise.week_022;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/6/24 16:24
 * @Version 1.0
 */
public class LeetCode_149_2_直线上最多的点数 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n ; i++) {
            Map<String, Integer> map = new HashMap<>();
            // 记录从i点出发的所有直线上的最多点数个数
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int a = x1 - x2, b = y1 - y2;
                int k = gcd(a, b);
                String key = (a / k) + "_" + (b / k);
                // 默认值为1，即包含i节点
                map.put(key, map.getOrDefault(key, 1) + 1);
                max = Math.max(max, map.get(key));
            }
            ans = Math.max(ans, max);
        }
        return ans;
    }

    /**
     * 求取两个数的最大公约数
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
