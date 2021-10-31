package main.java.com.exercise.week_039;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/10/26 18:45
 * @Version 1.0
 */
public class LeetCode_496_1_下一个更大元素I {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 用来存储nums的值，及它右边的第一个比它大的元素
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = nums2.length - 1; i >= 0 ; i--) {
            // 将栈顶比它小的值全pop掉
            while (!deque.isEmpty() && deque.peek() < nums2[i]) {
                deque.pop();
            }
            map.put(nums2[i], deque.isEmpty() ? -1 : deque.peek());
            deque.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length ; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
