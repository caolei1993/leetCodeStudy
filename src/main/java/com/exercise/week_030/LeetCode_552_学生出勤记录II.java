package main.java.com.exercise.week_030;

/**
 * @Author cl
 * @Date 2021/8/18 16:15
 * @Version 1.0
 */
public class LeetCode_552_学生出勤记录II {

    int mod = (int)1e9 + 7;
    int[][][] cache;

    public int checkRecord(int n) {
        cache = new int[n + 1][2][3];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j < 2 ; j++) {
                for (int k = 0; k < 3 ; k++) {
                    cache[i][j][k] = -1;
                }
            }
        }
        return dfs(n, 0, 0);
    }

    private int dfs(int u, int aCnt, int lCnt) {
        if (aCnt >= 2) {
            return 0;
        }
        if (lCnt >= 3) {
            return 0;
        }
        if (u == 0) {
            return 1;
        }
        if (cache[u][aCnt][lCnt] != -1) {
            return cache[u][aCnt][lCnt];
        }
        int ans = 0;
        // 添加A
        ans = dfs(u - 1, aCnt + 1, 0) % mod;
        // 添加L
        ans = (ans + dfs(u - 1, aCnt, lCnt + 1)) % mod;
        // 添加P
        ans = (ans + dfs(u - 1, aCnt, 0)) % mod;
        cache[u][aCnt][lCnt] = ans;
        return ans;
    }
}
