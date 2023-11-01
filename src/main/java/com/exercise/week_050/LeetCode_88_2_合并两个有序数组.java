package main.java.com.exercise.week_050;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/1 20:26
 */
public class LeetCode_88_2_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 初始化两个数组最后一个数据的坐标
        int p1 = m - 1, p2 = n - 1;
        // 合并后数组最后一位的坐标
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
