package main.java.com.exercise.week_048;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/5 9:56 下午
 */
public class LeetCode_1219_1_黄金矿工 {
    int[][] g;
    int mm, nn;
    int ans;
    boolean[][] flag;
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        g = grid;
        mm = m;
        nn = n;
        ans = 0;
        flag = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    flag[i][j] = true;
                    bfs(i, j, grid[i][j]);
                    flag[i][j] = false;
                }
            }
        }
        return ans;
    }

    private void bfs(int i, int j, int val) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        ans = Math.max(ans, val);
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= mm || y < 0 || y >= nn) {
                continue;
            }
            if (g[x][y] == 0 || flag[x][y]) {
                continue;
            }
            flag[x][y] = true;
            bfs(x, y, val + g[x][y]);
            flag[x][y] = false;
        }
    }
}
