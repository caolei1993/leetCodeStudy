package main.java.com.exercise.week_064;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/4/15 19:33
 */
public class LeetCode_146_1_LRU缓存 extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LeetCode_146_1_LRU缓存(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size() > capacity;
    }
}
