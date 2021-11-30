package main.java.com.exercise.week_040;

/**
 * @Author cl
 * @Date 2021/11/7 19:38
 * @Version 1.0
 */
public class LeetCode_598_1_范围求和II {
    public int maxCount(int m, int n, int[][] ops) {
        int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
        if (ops.length == 0) {
            return m * n;
        }
        for (int[] op : ops) {
            minx = Math.min(minx, op[0]);
            miny = Math.min(miny, op[1]);
        }
        return minx * miny;
    }
}
