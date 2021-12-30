package main.java.com.exercise.week_045;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/30 9:30 下午
 */
public class LeetCode_846_1_一手顺子 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        // 用来存入元素，保证每次都能取到最小值作为顺子的头
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        // 统计每个数子及出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : hand) {
            queue.offer(i);
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        while (!queue.isEmpty()) {
            int first = queue.poll();
            if (map.get(first) == 0) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int count = map.getOrDefault(first + i, 0);
                if (count == 0) {
                    return false;
                }
                map.put(first + i, count - 1);
            }
        }
        return true;
    }
}
