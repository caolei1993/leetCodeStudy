package main.java.com.exercise.week_011;

/**
 * @Author cl
 * @Date 2021/4/11 11:40
 * @Version 1.0
 */
public class LeetCode_264_2_丑数II {
    public int nthUglyNumber(int n) {
        int[] uglys = new int[n];
        uglys[0] = 1;
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 1; i < n; i++) {
            int value1 = uglys[p1] * 2;
            int value2 = uglys[p2] * 3;
            int value3 = uglys[p3] * 5;
            uglys[i] = Math.min(Math.min(value1, value2), value3);
            if (uglys[i] == value1) {
                p1++;
            }
            if (uglys[i] == value2) {
                p2++;
            }
            if (uglys[i] == value3) {
                p3++;
            }
        }
        return uglys[n - 1];
    }
}
