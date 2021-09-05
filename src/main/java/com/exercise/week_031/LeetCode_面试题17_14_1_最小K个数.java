package main.java.com.exercise.week_031;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/9/3 20:19
 * @Version 1.0
 */
public class LeetCode_面试题17_14_1_最小K个数 {
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }
}
