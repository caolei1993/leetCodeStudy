package main.java.com.exercise.week_022;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/6/25 14:35
 * @Version 1.0
 */
public class LeetCode_127_1_单词接龙 {
    String b;
    String e;
    Set<String> set = new HashSet<>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        b = beginWord;
        e = endWord;
        set.addAll(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        int t = dfs();
        return t == -1 ? 0 : t + 1;
    }

    private int dfs() {
        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();

        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        m1.put(b, 0);
        m2.put(e, 0);
        d1.add(b);
        d2.add(e);
        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            if (d1.size() <= d2.size()) {
                t = update(d1, m1, m2);
            } else {
                t = update(d2, m2, m1);
            }
            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    private int update(Deque<String> deque, Map<String, Integer> source, Map<String, Integer> other) {
        String v = deque.poll();
        int n = v.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                String s = v.substring(0, i) + (char)('a' + j) + v.substring(i + 1, n);
                if (set.contains(s)) {
                    if (source.containsKey(s)) {
                        continue;
                    }
                    if (other.containsKey(s)) {
                        return source.get(v) + 1 + other.get(s);
                    }
                    deque.add(s);
                    source.put(s, source.get(v) + 1);
                }
            }
        }
        return -1;
    }
}
