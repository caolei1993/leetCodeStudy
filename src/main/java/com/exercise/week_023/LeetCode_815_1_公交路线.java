package main.java.com.exercise.week_023;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/6/28 20:52
 * @Version 1.0
 */
public class LeetCode_815_1_公交路线 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // 公交的总的线路数
        int n = routes.length;
        // 定义线路图，所有线路看做点，可以换乘的线路间连线
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 遍历所有的线路
        for (int i = 0; i < n; i++) {
            // 遍历某条线路的所有车站
            for (int p : routes[i]) {
                List<Integer> list = map.getOrDefault(p, new ArrayList<>());
                // 遍历该站可以换乘的所有线路
                for (int j : list) {
                    // 可以换乘的线路之前连线
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                map.put(p, list);
            }
        }

        // 记录已经做过的线路
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Deque<Integer> deque = new ArrayDeque<>();
        // 起始站线路入队，并记录乘坐记录
        for (int w : map.getOrDefault(source, new ArrayList<>())) {
            dis[w] = 1;
            deque.add(w);
        }

        while (!deque.isEmpty()) {
            int x = deque.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    deque.add(y);
                }
            }
        }
        // 查看所有能到达的线路中是否有终点站所在的路线，并找出最短乘坐线路
        int ret = Integer.MAX_VALUE;
        for (int w : map.getOrDefault(target, new ArrayList<>())) {
            if (dis[w] != -1) {
                ret = Math.min(ret, dis[w]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
