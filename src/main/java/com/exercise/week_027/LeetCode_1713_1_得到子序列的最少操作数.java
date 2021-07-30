package main.java.com.exercise.week_027;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/7/26 21:15
 * @Version 1.0
 */
public class LeetCode_1713_1_得到子序列的最少操作数 {
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        // 将target数组中的每个值映射成为相应的坐标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(target[i], i);
        }
        // 将arr中在target中存在的元素转化成target的坐标，存入list
        List<Integer> list = new ArrayList<>();
        for (int a : arr) {
            if (map.containsKey(a)) {
                list.add(map.get(a));
            }
        }
        // 定义最长子序列的长度为0
        int len = list.size();
        int[] g = new int[len + 1];
        Arrays.fill(g, Integer.MAX_VALUE);
        int max = 0;
        for (Integer i : list) {
            int l = 0, r = len;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (g[mid] < i) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int clen = r + 1;
            g[clen] = Math.min(g[clen], i);
            max = Math.max(max, clen);
        }
        return n - max;
    }
}
