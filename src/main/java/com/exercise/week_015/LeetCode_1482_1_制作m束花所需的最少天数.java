package main.java.com.exercise.week_015;

/**
 * @Author cl
 * @Date 2021/5/9 17:18
 * @Version 1.0
 * https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/
 */
public class LeetCode_1482_1_制作m束花所需的最少天数 {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        // 不够凑成m束花
        if (n < m * k) {
            return -1;
        }

        int l = 0, r = (int)1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(bloomDay, mid, m, k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] bloomDay, int mid, int m, int k) {
        int bouquets = 0;
        int flowers = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= mid) {
                flowers++;
                if (flowers == k){
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}
