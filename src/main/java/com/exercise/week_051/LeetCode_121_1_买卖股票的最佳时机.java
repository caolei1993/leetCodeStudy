package main.java.com.exercise.week_051;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/8 20:27
 */
public class LeetCode_121_1_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                int profit = price - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}
