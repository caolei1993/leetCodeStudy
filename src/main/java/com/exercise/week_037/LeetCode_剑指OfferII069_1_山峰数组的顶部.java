package main.java.com.exercise.week_037;

/**
 * @Author cl
 * @Date 2021/10/14 9:18
 * @Version 1.0
 */
public class LeetCode_剑指OfferII069_1_山峰数组的顶部 {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid - 1] < arr[mid] ) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}
