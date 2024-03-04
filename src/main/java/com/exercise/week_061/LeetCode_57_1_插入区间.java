package main.java.com.exercise.week_061;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/23 17:00
 */
public class LeetCode_57_1_插入区间 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.add(newInterval);
        list.sort(Comparator.comparingInt(o -> o[0]));
        List<int[]> ansList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            int start = cur[0];
            int end = cur[1];
            while (i + 1 < list.size() && end >= list.get(i + 1)[0]) {
                end = Math.max(end, list.get(i + 1)[1]);
                i++;
            }
            ansList.add(new int[]{start, end});
        }
        return ansList.toArray(new int[ansList.size()][]);
    }
}
