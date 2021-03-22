package main.java.com.exercise.week_008;

/**
 * @Author cl
 * @Date 2021/3/11 20:40
 * @Version 1.0
 * https://leetcode-cn.com/problems/richest-customer-wealth/
 */
public class LeetCode_1672_1_最富有客户的资产总量 {

    public int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                sum += accounts[i][j];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
