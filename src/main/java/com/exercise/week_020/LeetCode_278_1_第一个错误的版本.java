package main.java.com.exercise.week_020;

/**
 * @Author cl
 * @Date 2021/6/15 17:55
 * @Version 1.0
 */
public class LeetCode_278_1_第一个错误的版本 {
    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while (left < right) {
            long tmp = (long)left + right >> 1;
            int mid = (int)tmp;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isBadVersion(int mid) {
        return false;
    }
}
