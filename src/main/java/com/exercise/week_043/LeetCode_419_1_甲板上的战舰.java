package main.java.com.exercise.week_043;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/18 6:14 下午
 */
public class LeetCode_419_1_甲板上的战舰 {
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                if (board[i][j] == 'X') {
                    ans++;
                }
            }
        }
        return ans;
    }
}
