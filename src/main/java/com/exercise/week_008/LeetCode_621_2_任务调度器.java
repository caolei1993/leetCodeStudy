package main.java.com.exercise.week_008;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/3/22 10:00
 * @Version 1.0
 */
public class LeetCode_621_2_任务调度器 {
    public int leastInterval(char[] tasks, int n) {
        int maxCount = 0;
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char s : tasks) {
            int value = map.getOrDefault(s, 0) + 1;
            if (value > maxCount) {
                count = 1;
            } else if (value == maxCount) {
                count++;
            }
            maxCount = Math.max(maxCount, value);
            map.put(s, value);

        }
        return Math.max((maxCount - 1) * (n+1) + count, tasks.length);
    }

}
