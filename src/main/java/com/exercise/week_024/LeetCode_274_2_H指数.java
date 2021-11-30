package main.java.com.exercise.week_024;

/**
 * @Author cl
 * @Date 2021/7/12 15:27
 * @Version 1.0
 */
public class LeetCode_274_2_H指数 {
    public int hIndex(int[] citations) {
        // 使用二分法求解
        int l = 0;
        int r = citations.length;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(citations, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] citations, int mid) {
        int value = 0;
        for (int c : citations) {
            if (c >= mid) {
                value++;
            }
        }
        return value >= mid;
    }
}
