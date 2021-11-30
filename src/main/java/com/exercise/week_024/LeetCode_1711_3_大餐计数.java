package main.java.com.exercise.week_024;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/7/8 9:13
 * @Version 1.0
 */
public class LeetCode_1711_3_大餐计数 {
    int mod = (int) 1e9 + 7;
    int max = 1 << 22;
    public int countPairs(int[] deliciousness) {
        long ans = 0;
        // 用于记录deliciousness中每个数字出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int d : deliciousness) {
            map.put(d, map.getOrDefault(d, 0) + 1);
        }
        for (int x : map.keySet()) {
            for (int i = 1; i < max; i = i << 1) {
                int t = i - x;
                if (map.containsKey(t)) {
                    if (x == t) {
                        ans += (long) (map.get(x) - 1) * map.get(t);
                    } else {
                        ans += (long) map.get(x) * map.get(t);
                    }
                }
            }
        }
        // 所有情况都计算了两次（x, y）和（y, x)）
        ans = ans >> 1;
        return (int)(ans % mod);
    }
}
