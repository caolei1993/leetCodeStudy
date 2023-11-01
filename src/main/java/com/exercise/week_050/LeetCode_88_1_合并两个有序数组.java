package main.java.com.exercise.week_050;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/1 19:50
 */
public class LeetCode_88_1_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        while (i >= 0) {
            if (m > 0 && n > 0) {
                if (nums1[m - 1] > nums2[n - 1]) {
                    nums1[i--] = nums1[--m];
                } else if (nums1[m - 1] < nums2[n - 1]) {
                    nums1[i--] = nums2[--n];
                } else {
                    nums1[i--] = nums2[--n];
                    nums1[i--] = nums1[--m];
                }
            } else {
                if (m > 0) {
                    nums1[i--] = nums1[--m];
                } else {
                    nums1[i--] = nums2[--n];
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        new LeetCode_88_1_合并两个有序数组().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
