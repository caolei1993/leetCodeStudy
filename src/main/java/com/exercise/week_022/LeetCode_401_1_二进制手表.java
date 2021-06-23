package main.java.com.exercise.week_022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cl
 * @Date 2021/6/21 14:48
 * @Version 1.0
 */
public class LeetCode_401_1_二进制手表 {
    static Map<Integer, List<String>> map = new HashMap<>();
    static {
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                int count = countOne(h) + countOne(m);
                List<String> list = map.getOrDefault(count, new ArrayList<>());
                list.add(getTime(h, m));
                map.put(count, list);
            }
        }
    }

    static String getTime (int hour, int minute) {
        return hour + ":" + (minute <= 9 ? "0" + minute : minute);
    }

    private static int countOne(int value) {
        int ans = 0;
        for (int i = value; i > 0 ; i -= lowbit(i)) {
            ans++;
        }
        return ans;
    }

    private static int lowbit(int i) {
        return i & -i;
    }

    public List<String> readBinaryWatch(int turnedOn) {
        return map.getOrDefault(turnedOn, new ArrayList<>());
    }
}
