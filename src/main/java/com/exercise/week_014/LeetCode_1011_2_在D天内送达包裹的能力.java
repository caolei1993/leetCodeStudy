package main.java.com.exercise.week_014;

/**
 * @Author cl
 * @Date 2021/4/26 14:54
 * @Version 1.0
 */
public class LeetCode_1011_2_在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int D) {
        int left = weights[0], right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        // 搜索区间为[left, right]
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] weights, int d, int mid) {
        int sum = 0;
        int count = 1;
        for (int i = 0; i < weights.length; i++) {
            if (sum + weights[i] > mid) {
                sum = weights[i];
                count++;
            } else {
                sum += weights[i];
            }
        }
        return count <= d;
    }
}
