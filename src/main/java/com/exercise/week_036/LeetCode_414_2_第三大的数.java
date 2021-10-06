package main.java.com.exercise.week_036;

/**
 * @Author cl
 * @Date 2021/10/6 11:08
 * @Version 1.0
 */
public class LeetCode_414_2_第三大的数 {
    static final long INF = Long.MIN_VALUE;
    public int thirdMax(int[] nums) {
        long a = INF, b = INF, c = INF;
        for (int num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (num < a && num > b) {
                c = b;
                b = num;
            } else if (num < b && num > c) {
                c = num;
            }
        }
        return (int)(c == INF ? a : c);
    }
}
