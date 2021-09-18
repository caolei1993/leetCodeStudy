package main.java.com.exercise.week_033;

/**
 * @Author cl
 * @Date 2021/9/18 14:03
 * @Version 1.0
 */
public class LeetCode_36_2_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[10][10], col = new boolean[10][10], area = new boolean[10][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if (row[i][u] || col[j][u] || area[idx][u]) {
                    return false;
                }
                row[i][u] = col[j][u] = area[idx][u] = true;
            }
        }
        return true;
    }
}
