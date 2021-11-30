package main.java.com.exercise.week_026;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/7/25 10:02
 * @Version 1.0
 */
public class LeetCode_1743_1_从相邻元素还原数组 {
    public int[] restoreArray(int[][] adjacentPairs) {
        int m = adjacentPairs.length, n = m + 1;
        Map<Integer, Integer> cnts = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            int a = pair[0], b = pair[1];
            // 统计每个数字出现的次数，只有首尾的数字只出现一次，其余位置均出现两次
            cnts.put(a, cnts.getOrDefault(a, 0) + 1);
            cnts.put(b, cnts.getOrDefault(b, 0) + 1);
            List<Integer> aList = map.getOrDefault(a, new ArrayList<>());
            aList.add(b);
            map.put(a, aList);
            List<Integer> bList = map.getOrDefault(b, new ArrayList<>());
            bList.add(a);
            map.put(b, bList);
        }
        // 首尾位置的数字出现次数为1
        int start = -1;
        for (int key : cnts.keySet()) {
            if (cnts.get(key) == 1) {
                start = key;
                break;
            }
        }
        int[] ans = new int[n];
        ans[0] = start;
        ans[1] = map.get(start).get(0);
        for (int i = 2; i < n; i++) {
            List<Integer> list = map.get(ans[i - 1]);
            for (int v : list) {
                if (v != ans[i - 2]) {
                    ans[i] = v;
                }
            }
        }
        return ans;
    }
}
