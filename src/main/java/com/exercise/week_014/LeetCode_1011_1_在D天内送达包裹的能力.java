package main.java.com.exercise.week_014;

/**
 * @Author cl
 * @Date 2021/4/26 11:18
 * @Version 1.0
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 */
public class LeetCode_1011_1_在D天内送达包裹的能力 {
    public static int shipWithinDays(int[] weights, int D) {
        int ans = weights[0];
        for (int weight : weights) {
            ans = Math.max(ans, weight);
        }
        int length = weights.length;

        while (true) {
            int value = weights[0];
            int count = 1;
            for (int i = 1; i < length; i++) {
                if (value + weights[i] > ans) {
                    value = weights[i];
                    count++;
                } else {
                    value += weights[i];
                }
            }
            if (count <= D) {
                return ans;
            } else {
                ans++;
            }
        }
    }

    public static void main(String[] args) {
        int[] weights = new int[]{1,2,3,1,1};
        System.out.println(shipWithinDays(weights, 4));
    }
}
