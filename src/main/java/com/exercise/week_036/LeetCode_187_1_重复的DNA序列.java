package main.java.com.exercise.week_036;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/10/8 18:04
 * @Version 1.0
 */
public class LeetCode_187_1_重复的DNA序列 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 10; i <= n; i++) {
            String subStr = s.substring(i - 10, i);
            int cnt = map.getOrDefault(subStr, 0);
            if (cnt == 1) {
                ans.add(subStr);
            }
            map.put(subStr, cnt + 1);
        }
        return ans;
    }
}
