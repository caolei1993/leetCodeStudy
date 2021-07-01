package main.java.com.exercise.week_023;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/7/1 15:12
 * @Version 1.0
 */
public class LeetCode_LCP07_1_传递信息 {
    // 记录每个人对应的传递关系
    Map<Integer, Set<Integer>>  map = new HashMap<>();
    public int numWays(int n, int[][] relation, int k) {
        // 遍历所有的传递关系，初始化map
        for (int[] re : relation) {
            Set<Integer> set = map.getOrDefault(re[0], new HashSet<>());
            set.add(re[1]);
            map.put(re[0], set);
        }
        int ans = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        for (int i = 0; i < k; i++) {
            Deque<Integer> deque1 = new ArrayDeque<>();
            if (deque.isEmpty()) {
                return 0;
            }
            while (!deque.isEmpty()) {
                int a = deque.poll();
                Set<Integer> ways = map.getOrDefault(a, new HashSet<>());
                for (int way : ways) {
                    if (i == k - 1 && way == n - 1) {
                        ans++;
                    }
                    deque1.offer(way);
                }
            }
            deque = deque1;
        }
        return ans;
    }
}
