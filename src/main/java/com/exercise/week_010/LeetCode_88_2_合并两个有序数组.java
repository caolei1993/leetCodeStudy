package main.java.com.exercise.week_010;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/4/5 13:11
 * @Version 1.0
 */
public class LeetCode_88_2_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 初始化尾部双指针
        int p1 = m - 1, p2 = n - 1;
        // 尾部
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
