package main.java.com.exercise.week_047;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/15 10:23 下午
 */
public class LeetCode_1716_1_计算力扣银行的钱 {
    public int totalMoney(int n) {
        int ans = 0;
        int start = 1;
        for(int i = 1; i <= n;) {
            int val = start;
            for (int j = 1; j <= 7 && i <= n; j++) {
                ans += val++;
                i++;
                if (j == 7) {
                    start++;
                }
            }
        }
        return ans;
    }
}
