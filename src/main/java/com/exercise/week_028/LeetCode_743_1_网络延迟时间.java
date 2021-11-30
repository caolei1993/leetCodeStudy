package main.java.com.exercise.week_028;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author cl
 * @Date 2021/8/2 14:15
 * @Version 1.0
 */
public class LeetCode_743_1_网络延迟时间 {
    /**
     * 节点个数最大值100
     */
    static final int N = 110;
    /**
     * 有向边的条数最大值6000
     */
    static final int M = 6010;

    int[] head = new int[N];
    int[] end = new int[M];
    int[] next = new int[M];
    int[] weight = new int[M];
    int idx;
    int INF = Integer.MAX_VALUE;
    int[] dist = new int[N];
    boolean[] vis = new boolean[N];
    int K;

    private void add (int s, int e, int w) {
        end[idx] = e;
        next[idx] = head[s];
        head[s] = idx;
        weight[idx] = w;
        idx++;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        K = k;
        Arrays.fill(head, -1);
        // 将所有的有向边初始化到图中
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            add(u, v , w);
        }
        spfa();
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    private void spfa() {
        Arrays.fill(dist, INF);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);
        dist[K] = 0;
        vis[K] = true;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (int i = head[poll]; i != -1 ; i = next[i]) {
                int val = end[i];
                vis[val] = false;
                if (dist[val] > dist[poll] + weight[i]) {
                    dist[val] = dist[poll] + weight[i];
                    if (vis[val]) {
                        continue;
                    }
                    queue.offer(val);
                    vis[val] = true;
                }
            }
        }
    }
}
