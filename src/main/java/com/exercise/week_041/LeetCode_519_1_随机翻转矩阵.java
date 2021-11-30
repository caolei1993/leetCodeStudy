package main.java.com.exercise.week_041;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LeetCode_519_1_随机翻转矩阵 {
    Map<Integer, Integer> map = new HashMap<>();
    int m;
    int n;
    int size;
    Random random = new Random(300);
    public LeetCode_519_1_随机翻转矩阵(int m, int n) {
        this.m = m;
        this.n = n;
        reset();
    }

    public int[] flip() {
        int x = random.nextInt(size--);
        int y = map.getOrDefault(x, x);
        map.put(x, map.getOrDefault(size, size));
        return new int[]{y / n, y % n};
    }

    public void reset() {
        map.clear();
        size = m * n;
    }
}
