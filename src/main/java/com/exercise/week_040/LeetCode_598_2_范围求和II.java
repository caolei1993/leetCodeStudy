package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/7 20:19
 * @Version 1.0
 */
public class LeetCode_598_2_范围求和II {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] p : ops) {
            m = Math.min(m, p[0]);
            n = Math.min(n, p[1]);
        }
        return m * n;
    }
}
