package main.java.com.exercise.week_043;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/19 6:24 下午
 */
public class LeetCode_997_1_找到小镇的法官 {
    public static int findJudge(int n, int[][] trust) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        if (n == 1) {
            return 1;
        }
        for (int[] i : trust) {
            // 信任者编号
            int a = i[0];
            // 被信任者编号
            int b = i[1];
            set.add(a);
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        for (int j : set) {
            map.remove(j);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == n - 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}};
        System.out.println(findJudge(4, a));
    }
}
