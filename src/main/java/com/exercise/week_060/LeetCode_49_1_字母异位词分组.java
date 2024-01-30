package main.java.com.exercise.week_060;

import java.util.*;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/29 09:59
 */
public class LeetCode_49_1_字母异位词分组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String s = new String(arr);
            List list = map.getOrDefault(s, new ArrayList<>());
            list.add(str);
            map.put(s, list);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> l : map.values()) {
            ans.add(l);
        }
        return ans;
    }
}
