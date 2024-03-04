package main.java.com.exercise.week_061;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/26 17:35
 */
public class LeetCode_452_1_用最少数量的箭引爆气球 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(value -> value[1]));
        int ans = 1;
        int point = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int[] cur = points[i];
            int start = cur[0];
            int end = cur[1];
            if (start > point) {
                point = end;
                ans++;
            }
        }
        return ans;
    }
}
