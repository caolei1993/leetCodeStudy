package main.java.com.exercise.week_047;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/1/15 10:32 下午
 */
public class LeetCode_1716_2_计算力扣银行的钱 {
    public int totalMoney(int n) {
        int a = n / 7, b = n % 7;
        int a1 = (1 + 1 + 6) * 7 / 2, an = (a + a + 6) * 7 / 2;
        int ans = (a1 + an) * a / 2;
        a++;
        ans += (a + a + b - 1) * b / 2;
        return ans;
    }
}
