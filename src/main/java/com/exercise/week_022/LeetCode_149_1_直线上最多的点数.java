package main.java.com.exercise.week_022;

/**
 * @Author cl
 * @Date 2021/6/24 10:39
 * @Version 1.0
 */
public class LeetCode_149_1_直线上最多的点数 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n ; i++) {
            int cnt = 1;
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] y = points[j];
                cnt = 2;
                for (int k = j + 1; k < n; k++) {
                    int[] p = points[k];
                    int s1 = (y[1] - x[1]) * (p[0] - y[0]);
                    int s2 = (y[0] - x[0]) * (p[1] - y[1]);
                    if (s1 == s2) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }
}
