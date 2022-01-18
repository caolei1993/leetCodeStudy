package main.java.com.exercise.week_047;

import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/18 10:32 下午
 */
public class LeetCode_539_2_最小时间差 {
    public int findMinDifference(List<String> timePoints) {
        int size = timePoints.size();
        if (size > 1440) {
            return 0;
        }
        int[] cnt = new int[1440 * 2];
        for (String timePoint : timePoints) {
            String[] time = timePoint.split(":");
            int h = Integer.parseInt(time[0]);
            int m = Integer.parseInt(time[1]);
            cnt[h * 60 + m]++;
            cnt[h * 60 + m + 1440]++;
        }
        int ans = Integer.MAX_VALUE, last = -1;
        for (int i = 0; i < 1440 * 2 && ans != 0; i++) {
            if (cnt[i] == 0) {
                continue;
            }
            if (cnt[i] > 1) {
                return 0;
            }
            if (last != -1) {
                ans = Math.min(ans, i - last);
            }
            last = i;
        }
        return ans;
    }
}
