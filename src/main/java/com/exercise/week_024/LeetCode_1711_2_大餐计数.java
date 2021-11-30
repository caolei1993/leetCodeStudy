package main.java.com.exercise.week_024;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/7/7 21:55
 * @Version 1.0
 */
public class LeetCode_1711_2_大餐计数 {
    public int countPairs(int[] deliciousness) {
        int mod = (int) 1e9 + 7;
        long ans = 0;
        int len = deliciousness.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int other : map.keySet()) {
                if (check(deliciousness[i] + other)) {
                    ans += map.get(other);
                }
            }
            map.put(deliciousness[i], map.getOrDefault(deliciousness[i], 0) + 1);
        }
        return (int)(ans % mod);
    }
    private boolean check (int n) {
        return n != 0 && (n & -n) == n;
    }
}
