package main.java.com.exercise.week_045;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/30 10:01 下午
 */
public class LeetCode_825_1_适龄的朋友 {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length, ans = 0;
        for (int k = 0, i = 0, j = 0; k < n; k++) {
            while(i < k && !check(ages[i], ages[k])) {
                i++;
            }
            if (j < k) {
                j = k;
            }
            while(j < n && check(ages[j], ages[k])) {
                j++;
            }
            if (i < j) {
                ans += j - i - 1;
            }
        }
        return ans;
    }

    private boolean check(int x, int y) {
        if (y <= x / 2 + 7) {
            return false;
        }
        if (y > x) {
            return false;
        }
        if (y > 100 && x < 100) {
            return false;
        }
        return true;
    }
}
