package main.java.com.exercise.week_023;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/7/2 15:43
 * @Version 1.0
 */
public class LeetCode_1833_1_雪糕的最大数量 {
    public int maxIceCream(int[] costs, int coins) {
        int num = 0;
        Arrays.sort(costs);
        for (int c : costs) {
            if (coins >= c) {
                coins -= c;
                num++;
            } else {
                break;
            }
        }
        return num;
    }
}
