package main.java.com.exercise.week_049;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/25 5:22 下午
 */
public class LeetCode_1706_1_球会落何处 {
    int m, n;
    int[][] g;
    public int[] findBall(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        g = grid;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = getVal(i);
        }
        return ans;
    }
    private int getVal(int i) {
        int r = 0, c = i;
        while (r < m) {
            int ne = c + g[r][c];
            if (ne < 0 || ne >= n) {
                return -1;
            }
            if (g[r][c] != g[r][ne]) {
                return -1;
            }
            r++;
            c = ne;
        }
        return c;
    }
}
