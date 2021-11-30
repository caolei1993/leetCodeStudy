package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/12 16:13
 * @Version 1.0
 */
public class LeetCode_275_2_H指数II {
    public int hIndex(int[] citations) {
        int n = citations.length;

        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            //
            if (citations[mid] >= n - mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return citations[r] >= n - r ? n - r : 0;
    }
}
