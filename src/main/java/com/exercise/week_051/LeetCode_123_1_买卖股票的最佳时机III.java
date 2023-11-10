package main.java.com.exercise.week_051;

/**
 * @author cl
 * @version 1.0
 * @description： 暴力方法（超时）
 * @date 2023/11/10 10:43
 */
public class LeetCode_123_1_买卖股票的最佳时机III {
    public int maxProfit(int[] prices) {
        int len  = prices.length;
        int maxProfit = 0;
        for (int i = 0; i < len - 1; i++) {
            int sum = subMaxProfit(prices, 0, i) + subMaxProfit(prices, i , len - 1);
            if (sum > maxProfit) {
                maxProfit = sum;
            }
        }
        return maxProfit;
    }

    private int subMaxProfit(int[] prices, int start, int end) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = start; i <= end; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int profit = prices[i] - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1,2};
        System.out.println(new LeetCode_123_1_买卖股票的最佳时机III().maxProfit(prices));
    }
}
