package main.java.com.exercise.week_008;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/3/10 21:15
 * @Version 1.0
 * https://leetcode-cn.com/problems/task-scheduler/
 */
public class LeetCode_621_1_任务调度器 {
    public int leastInterval(char[] tasks, int n) {
        int maxCount = 0;
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char s : tasks) {
            int value = map.getOrDefault(s, 0) + 1;
            maxCount = Math.max(maxCount, value);
            map.put(s, value);
        }

        Set<Map.Entry<Character, Integer>> set = map.entrySet();
        for (Map.Entry<Character, Integer> entry : set) {
            if (entry.getValue() == maxCount) {
                count++;
            }
        }

        return Math.max((maxCount - 1) * (n+1) + count, tasks.length);
    }
}
