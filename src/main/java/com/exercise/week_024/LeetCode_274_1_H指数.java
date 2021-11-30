package main.java.com.exercise.week_024;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/7/12 9:57
 * @Version 1.0
 */
public class LeetCode_274_1_H指数 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
