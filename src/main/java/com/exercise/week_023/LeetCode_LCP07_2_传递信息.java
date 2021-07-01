package main.java.com.exercise.week_023;

/**
 * @Author cl
 * @Date 2021/7/1 16:01
 * @Version 1.0
 */
public class LeetCode_LCP07_2_传递信息 {
    public int numWays(int n, int[][] relation, int k) {

        // 定义dp二维数组，f[i][j]代表走了i步，当前位置在坐标为j的位置的方案总数
        int[][] f = new int[k + 1][n];
        // 初始化走0步，在index=0的位置的方案为1
        f[0][0] = 1;
        for (int i = 1; i <= k ; i++) {
            for (int[] r : relation) {
                int a = r[0], b = r[1];
                f[i][b] += f[i - 1][a];
            }
        }
        return f[k][n - 1];
    }
}
