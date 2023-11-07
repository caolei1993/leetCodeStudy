package main.java.com.exercise.week_051;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/6 20:33
 */
public class LeetCode_169_1_多数元素 {
    public int majorityElement(int[] nums) {
        int length = nums.length;
        int count = (int)Math.ceil(length /2.0);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int cur = map.getOrDefault(nums[i], 0);
            cur++;
            if (cur == count) {
                return nums[i];
            }
            map.put(nums[i], cur);
        }
        return 0;
    }
}
