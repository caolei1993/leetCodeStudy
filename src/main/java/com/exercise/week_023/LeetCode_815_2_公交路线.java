package main.java.com.exercise.week_023;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/6/29 17:19
 * @Version 1.0
 */
public class LeetCode_815_2_公交路线 {
    // 记录某个车站能换乘的路线
    Map<Integer, Set<Integer>> bus = new HashMap<>();
    int s, t;
    int[][] r;
    // 最大车站为10^6，所以初始化并查集集合
    int max = (int)1e6 + 10;
    int[] p = new int[max];
    private int find (int x) {
        if (x != p[x]) {
            x = find(p[x]);
        }
        return x;
    }

    private void union (int x, int y) {
        p[find(x)] = p[find(y)];
    }

    private boolean query (int x, int y) {
        return find(x) == find(y);
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        s = source;
        t = target;
        r = routes;
        if (s == t) {
            return 0;
        }
        // 并查集排除不能到的情况
        for (int i = 0; i < max; i++) {
            p[i] = i;
        }
        for (int[] rou : routes) {
            for (int station : rou) {
                union(station, rou[0]);
            }
        }
        if (!query(s, t)) {
            return -1;
        }

        return dfs();
    }

    private int dfs() {
        Deque<Integer> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        Map<Integer, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();

        // 初始化map,记录每个车站能换成的线路
        for (int i = 0; i < r.length; i++) {
            int n = r[i].length;
            for (int j = 0; j < n; j++) {
                int st = r[i][j];
                if (st == s) {
                    d1.add(i);
                    m1.put(i, 1);
                }
                if (st == t) {
                    d2.add(i);
                    m2.put(i, 1);
                }
                Set<Integer> set = bus.getOrDefault(st, new HashSet<>());
                set.add(i);
                bus.put(st, set);
            }
        }
        Set<Integer> s1 = bus.getOrDefault(s, new HashSet<>()), s2 = bus.getOrDefault(t, new HashSet<>());
        Set<Integer> ss = new HashSet<>();
        ss.addAll(s1);
        ss.retainAll(s2);
        if (!ss.isEmpty()) {
            return 1;
        }

        int v;
        while (!d1.isEmpty() && !d2.isEmpty()) {
            if (d1.size() <= d2.size()) {
                v = update(d1, m1, m2);
            } else {
                v = update(d2, m2, m1);
            }
            if (v != -1) {
                return v;
            }
        }
        return -1;
    }

    private int update(Deque<Integer> deque, Map<Integer, Integer> source, Map<Integer, Integer> other) {
        Integer p = deque.poll();
        // 遍历当前线路上的所有站点
        int[] w = r[p];
        for (int station : w) {
            Set<Integer> ways = bus.get(station);
            for (Integer way : ways) {
                if (source.containsKey(way)) {
                    continue;
                }
                if (other.containsKey(way)) {
                    return source.get(p) + other.get(way);
                }
                deque.add(way);
                source.put(way, source.get(p) + 1);
            }
        }
        return -1;
    }
}
