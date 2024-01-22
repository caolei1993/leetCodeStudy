package main.java.com.exercise.week_058;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/17 17:48
 */
public class LeetCode_36_1_有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][][] box = new int[3][3][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int index = c - '0' - 1;
                row[i][index] += 1;
                col[j][index] += 1;
                box[i/3][j/3][index] += 1;
                if (row[i][index] > 1 || col[j][index] > 1 || box[i/3][j/3][index] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char c = '0';
        System.out.println(c - '0');
    }
}
