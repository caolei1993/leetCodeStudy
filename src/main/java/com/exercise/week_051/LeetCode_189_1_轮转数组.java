package main.java.com.exercise.week_051;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/7 20:17
 */
public class LeetCode_189_1_轮转数组 {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = len - 1; i >= 0; i--) {
            queue.offer(nums[i]);
        }
        k %= len;
        for (int i = 0; i < k; i++) {
            int cur = queue.poll();
            queue.offer(cur);
        }
        for (int i = len - 1; i >= 0; i--) {
            nums[i] = queue.poll();
        }
    }

    public static void main(String[] args) {
        int nums[] = new int[]{1,2,3,4,5,6,7};
        new LeetCode_189_1_轮转数组().rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
