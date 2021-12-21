package main.java.com.exercise.week_044;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/20 8:46 下午
 */
public class LeetCode_475_1_供暖器 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 0, r = (int)1e9;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(houses, heaters, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean check(int[] houses, int[] heaters, int x) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && heaters[j] + x < houses[i]) {
                j++;
            }
            if (j < m && heaters[j] - x <= houses[i] && heaters[j] + x >= houses[i]) {
                continue;
            }
            return false;
        }
        return true;
    }
}
