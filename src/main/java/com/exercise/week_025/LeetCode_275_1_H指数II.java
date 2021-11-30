package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/12 15:59
 * @Version 1.0
 */
public class LeetCode_275_1_H指数II {
    public int hIndex(int[] citations) {
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] > h) {
                h++;
            }
        }
        return h;
    }
}
