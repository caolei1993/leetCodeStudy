package main.java.com.exercise.week_059;

import java.util.Arrays;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/23 18:23
 */
public class LeetCode_289_1_生命游戏 {
    int[][] boards;
    int m, n;

    public void gameOfLife(int[][] board) {
        m = board.length;
        n = board[0].length;
        boards = board;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = calculateCount(i, j);
                if (board[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        board[i][j] = -1;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    public int calculateCount(int i, int j) {
        int count = 0;
        if (i - 1 >= 0) {
            if (j - 1 >= 0) {
                count = boards[i - 1][j - 1] == 1 || boards[i - 1][j - 1] == -1 ? count + 1 : count;
            }
            if (j + 1 < n) {
                count = boards[i - 1][j + 1] == 1 || boards[i - 1][j + 1] == -1 ? count + 1 : count;
            }
            count = boards[i - 1][j] == 1 || boards[i - 1][j] == -1 ? count + 1 : count;
        }

        if (i + 1 < m) {
            if (j - 1 >= 0) {
                count = boards[i + 1][j - 1] == 1 || boards[i + 1][j - 1] == -1 ? count + 1 : count;
            }
            if (j + 1 < n) {
                count = boards[i + 1][j + 1] == 1 || boards[i + 1][j + 1] == -1 ? count + 1 : count;
            }
            count = boards[i + 1][j] == 1 || boards[i + 1][j] == -1 ? count + 1 : count;
        }

        if (j - 1 >= 0) {
            count = boards[i][j - 1] == 1 || boards[i][j - 1] == -1 ? count + 1 : count;
        }
        if (j + 1 < n) {
            count = boards[i][j + 1] == 1 || boards[i][j + 1] == -1 ? count + 1 : count;
        }
        return count;
    }

    public static void main(String[] args) {

        int a[][] = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        new LeetCode_289_1_生命游戏().gameOfLife(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
        }
        System.out.println();
    }
}
