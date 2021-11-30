package main.java.com.exercise.week_025;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/7/16 13:52
 * @Version 1.0
 */
public class LeetCode_1846_1_减小和重新排列数组后的最大元素 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[n - 1];
    }
}
