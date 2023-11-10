package main.java.com.exercise.week_051;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/10 09:55
 */
public class LeetCode_122_1_买卖股票的最佳时机II {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price > minPrice) {
                maxProfit += (price - minPrice);
            }
            minPrice = price;
        }
        return maxProfit;
    }
}
