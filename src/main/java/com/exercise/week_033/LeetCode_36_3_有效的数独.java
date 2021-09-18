package main.java.com.exercise.week_033;

/**
 * @Author cl
 * @Date 2021/9/18 14:15
 * @Version 1.0
 */
public class LeetCode_36_3_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[10], col = new int[10], area = new int[10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if (((row[i] >> u) & 1) == 1 || ((col[j] >> u) & 1) == 1 || ((area[idx] >> u) & 1) == 1) {
                    return false;
                }
                row[i] |= 1 << u;
                col[j] |= 1 << u;
                area[idx] |= 1 << u;
            }
        }
        return true;
    }
}
