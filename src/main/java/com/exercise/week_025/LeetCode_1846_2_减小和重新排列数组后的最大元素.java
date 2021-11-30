package main.java.com.exercise.week_025;

/**
 * @Author cl
 * @Date 2021/7/16 15:13
 * @Version 1.0
 */
public class LeetCode_1846_2_减小和重新排列数组后的最大元素 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        // 初始化记录每个值的数量的数组，大于n的按n记录
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        // 数值从1开始，题目规定值为正整数 (>=1)
        for (int i = 1; i <= n; i++) {
            if (cnt[i] == 0) {
                miss++;
            } else {
                miss -= Math.min(cnt[i] - 1, miss);
            }
        }
        return n - miss;
    }
}
