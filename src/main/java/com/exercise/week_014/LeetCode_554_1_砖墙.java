package main.java.com.exercise.week_014;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/5/5 21:17
 * @Version 1.0
 * https://leetcode-cn.com/problems/brick-wall/
 */
public class LeetCode_554_1_砖墙 {
    public int leastBricks(List<List<Integer>> wall) {
        int size = wall.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, sum = 0; i < size; i++, sum = 0) {
            for (int cur : wall.get(i)) {
                sum += cur;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            // 因为不包含最后的边界，所以去除整行sum的缝隙记录
            map.remove(sum);
        }
        int ans = size;
        for (int cur : map.keySet()) {
            ans = Math.min(ans, size - map.get(cur));
        }
        return ans;
    }
}
