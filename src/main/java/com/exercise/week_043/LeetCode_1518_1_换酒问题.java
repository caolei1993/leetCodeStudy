package main.java.com.exercise.week_043;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/17 9:25 下午
 */
public class LeetCode_1518_1_换酒问题 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles, change = numBottles;
        while (change >= numExchange) {
            // 交换获得的酒
            int a = change / numExchange;
            ans += a;
            change %= numExchange;
            change += a;
        }
        return ans;
    }
}
