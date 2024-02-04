package main.java.com.exercise.week_060;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/1 17:30
 */
public class LeetCode_219_1_存在重复元素II {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                if (i - map.get(num) <= k) {
                    return true;
                }
            }
            map.put(num, i);
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "20240125170533110626698009566000";
        System.out.println(str.substring(16, 21));
    }

}
