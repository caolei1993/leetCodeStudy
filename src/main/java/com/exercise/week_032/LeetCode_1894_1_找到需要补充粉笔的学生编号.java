package main.java.com.exercise.week_032;

/**
 * @Author cl
 * @Date 2021/9/10 15:55
 * @Version 1.0
 */
public class LeetCode_1894_1_找到需要补充粉笔的学生编号 {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        long[] sum = new long[len];
        sum[0] = chalk[0];
        for (int i = 1; i < len ; i++) {
            sum[i] = sum[i - 1] + chalk[i];
        }

        k %= sum[len - 1];

        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (sum[mid] <= k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return sum[l] > k ? l : l + 1;
    }
}
