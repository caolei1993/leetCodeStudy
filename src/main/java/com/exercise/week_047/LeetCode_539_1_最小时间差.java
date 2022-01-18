package main.java.com.exercise.week_047;

import java.util.Collections;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/18 10:31 下午
 */
public class LeetCode_539_1_最小时间差 {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int preMinute = getMinute(timePoints.get(0));
        int startMinute = preMinute;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); i++) {
            int minute = getMinute(timePoints.get(i));
            ans = Math.min(ans, minute - preMinute);
            preMinute = minute;
        }
        ans = Math.min(ans, startMinute + 1440 - preMinute);
        return ans;
    }

    private int getMinute(String time) {
        return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 + (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
    }
}
