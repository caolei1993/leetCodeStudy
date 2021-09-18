package main.java.com.exercise.week_033;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/9/16 16:16
 * @Version 1.0
 */
public class LeetCode_212_1_单词搜索II {
    static class TireNode {
        String s;
        TireNode[] tns = new TireNode[26];
    }

    TireNode root = new TireNode();
    Set<String> set = new HashSet<>();
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    char[][] boards;
    int m,n;
    /**
     * 标记某个点是否已经遍历过
     */
    boolean[][] vis = new boolean[15][15];

    void insert (String s) {
        TireNode p = root;
        char[] cc = s.toCharArray();
        for (char c : cc) {
            int v = c - 'a';
            if (p.tns[v] == null) {
                p.tns[v] = new TireNode();
            }
            p = p.tns[v];
        }
        p.s = s;
    }

    public List<String> findWords(char[][] board, String[] words) {
        boards = board;
        m = board.length;
        n = board[0].length;
        for (String s : words) {
            insert(s);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int u = board[i][j] - 'a';
                if (root.tns[u] != null) {
                    vis[i][j] = true;
                    dfs(i, j, root.tns[u]);
                    vis[i][j] = false;
                }
            }
        }
        return new ArrayList<>(set);
    }

    private void dfs(int i, int j, TireNode node) {
        if (node.s != null) {
            set.add(node.s);
        }

        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (vis[x][y]) {
                continue;
            }
            int v = boards[x][y] - 'a';
            if (node.tns[v] != null) {
                vis[x][y] = true;
                dfs(x, y, node.tns[v]);
                vis[x][y] = false;
            }
        }
    }
}
