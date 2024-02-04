package main.java.com.exercise.week_060;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/4 13:44
 */
public class LeetCode_128_1_最长连续序列 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int len = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    len++;
                }
                max = Math.max(len, max);
            }
        }
        return max;
    }
}
