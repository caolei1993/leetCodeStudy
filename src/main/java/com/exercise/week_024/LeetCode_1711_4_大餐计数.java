package main.java.com.exercise.week_024;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/7/9 17:45
 * @Version 1.0
 */
public class LeetCode_1711_4_大餐计数 {
    int mod = (int) 1e9 + 7;
    int max = 1 << 22;
    public int countPairs(int[] deliciousness) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : deliciousness) {
            for (int i = 1; i < max; i <<= 1) {
                int t = i - x;
                if (map.containsKey(t)) {
                    ans += map.get(t);
                    ans %= mod;
                }
            }
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        return ans;
    }
}
