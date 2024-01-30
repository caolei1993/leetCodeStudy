package main.java.com.exercise.week_060;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/30 14:52
 */
public class LeetCode_1_1_两数之和 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < len; i++) {
            int value = target - nums[i];
            if (map.containsKey(value)) {
                int index = map.get(value);
                if (index != i) {
                    return new int[]{i, index};
                }
            }
        }
        return new int[]{};
    }
}
