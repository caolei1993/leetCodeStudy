package main.java.com.exercise.week_021;

/**
 * @Author cl
 * @Date 2021/6/16 17:19
 * @Version 1.0
 */
public class LeetCode_852_2_山脉数组的峰顶索引 {
    public  int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            long tem = (long)l + r >> 1;
            int mid = (int)tem;
            if (arr[mid] < arr[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
