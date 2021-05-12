package main.java.com.exercise.week_015;

/**
 * @Author cl
 * @Date 2021/5/5 22:11
 * @Version 1.0
 * https://leetcode-cn.com/problems/delete-and-earn/
 */
public class LeetCode_740_1_删除并获得点数 {
    int[] cnts = new int[10001];
    public int deleteAndEarn(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            cnts[num]++;
            max = Math.max(max, num);
        }
        // f[i][0]代表i未被选择的获得的所有点数，f[i][1]代表i被选择获得的所有点数
        int[][] f = new int[max + 1][2];
        for (int i = 1; i <= max; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
            f[i][1] = f[i - 1][0] + i * cnts[i];
        }
        return Math.max(f[max][0], f[max][1]);
    }
}
