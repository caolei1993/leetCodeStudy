package main.java.com.exercise.week_009;

import java.util.TreeMap;

/**
 * @Author cl
 * @Date 2021/3/24 10:06
 * @Version 1.0
 * https://leetcode-cn.com/problems/132-pattern/
 */
public class LeetCode_456_1_132模式 {
    public boolean find132pattern(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        // 遍历值左侧的最小值即132中的1
        int minLeft = nums[0];
        // 遍历值右侧的有序结构，用来判断是否有3
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int k = 2; k < length; k++) {
            map.put(nums[k], map.getOrDefault(nums[k], 0) + 1);
        }
        // 相当于遍历下标j的元素
        for (int j = 1; j < length - 1; j++) {
            if (nums[j] > minLeft) {
                Integer ceilMinValue = map.ceilingKey(minLeft + 1);
                if (ceilMinValue != null && nums[j] > ceilMinValue) {
                    return true;
                }
            }
            minLeft = Math.min(minLeft, nums[j]);
            // 将map中保存的下一个要遍历的元素剔除
            map.put(nums[j+1], map.get(nums[j+1]) - 1);
            if (map.get(nums[j+1]) == 0) {
                map.remove(nums[j+1]);
            }
        }
        return false;
    }
}
