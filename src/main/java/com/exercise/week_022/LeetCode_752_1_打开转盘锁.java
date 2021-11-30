package main.java.com.exercise.week_022;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/6/27 16:25
 * @Version 1.0
 */
public class LeetCode_752_1_打开转盘锁 {
    String s, t;
    Set<String> set = new HashSet<>();
    public int openLock(String[] deadends, String target) {
        s = "0000";
        t = target;
        set.addAll(Arrays.asList(deadends));
        if (s.equals(t)) {
            return 0;
        }
        if (set.contains(s)) {
            return -1;
        }
        return dfs();
    }

    private int dfs() {
        int a = -1;
        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        d1.add(s);
        d2.add(t);
        m1.put(s, 0);
        m2.put(t, 0);
        while (!d1.isEmpty() && !d2.isEmpty()) {
            if (d1.size() <= d2.size()) {
                a = update(d1, m1, m2);
            } else {
                a = update(d2, m2, m1);
            }
            if (a != -1) {
                return a;
            }
        }
        return -1;
    }

    private int update(Deque<String> deque, Map<String, Integer> source, Map<String, Integer> other) {
        String v = deque.poll();
        // 转盘4位
        for (int i = 0; i < 4; i++) {
            // 每一位都可以+1或者-1
            for (int j = -1; j <= 1; j++) {
                if (j == 0) {
                    continue;
                }

                int origin = v.charAt(i) - '0';
                int next = (origin + j) % 10;
                if (next == -1) {
                    next = 9;
                }
                // 拼接经过一次转动的转盘数据
                String newS = v.substring(0, i) + next + v.substring(i + 1);
                // 如果存在于卡死集合中，跳过
                if (set.contains(newS)) {
                    continue;
                }
                // 如果存在于原map中，代表已经转到过，跳过
                if (source.containsKey(newS)) {
                    continue;
                }
                // 如果存在于other中，代表找到了最短路径
                if (other.containsKey(newS)) {
                    return source.get(v) + 1 + other.get(newS);
                }
                source.put(newS, source.get(v) + 1);
                deque.add(newS);
            }
        }
        return -1;
    }
}
