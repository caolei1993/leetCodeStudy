package main.java.com.exercise.week_035;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/10/1 18:03
 * @Version 1.0
 */
public class LeetCode_1436_1_旅行的终点站 {
    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String v = entry.getValue();
            if (!map.containsKey(v)) {
                return v;
            }
        }
        return null;
    }
}
