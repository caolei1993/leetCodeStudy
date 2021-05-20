package main.java.com.exercise.week_017;

import java.util.*;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/20 17:16
 */
public class LeetCode_692_1_前K个高频单词 {
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a, b) -> {
            if (a.getValue() == b.getValue()) {
                return a.getKey().compareTo(b.getKey());
            } else {
                return b.getValue() - a.getValue();
            }
            });
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            queue.add(entry);
        }
        while (!queue.isEmpty()) {
            ans.add(queue.poll().getKey());
        }
        System.out.println(ans);
        return ans.subList(0, k);
    }

    public static void main(String[] args) {
        String[] str = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent(str, 1));
    }
}
