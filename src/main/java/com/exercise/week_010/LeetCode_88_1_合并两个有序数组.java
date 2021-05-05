package main.java.com.exercise.week_010;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/4/5 9:51
 * @Version 1.0
 */
public class LeetCode_88_1_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}
