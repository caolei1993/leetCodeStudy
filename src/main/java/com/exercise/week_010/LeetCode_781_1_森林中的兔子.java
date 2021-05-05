package main.java.com.exercise.week_010;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/4/4 21:24
 * @Version 1.0
 * https://leetcode-cn.com/problems/rabbits-in-forest/
 */
public class LeetCode_781_1_森林中的兔子 {
    public int numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : answers) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // 某种颜色兔子总数
            int count = entry.getKey() + 1;
            ans += Math.ceil(((double) entry.getValue())/count) * count;
        }
        return ans;
    }
}
