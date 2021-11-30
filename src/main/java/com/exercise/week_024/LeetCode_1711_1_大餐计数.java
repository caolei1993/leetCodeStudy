package main.java.com.exercise.week_024;

/**
 * @Author cl
 * @Date 2021/7/7 21:31
 * @Version 1.0
 */
public class LeetCode_1711_1_大餐计数 {
    public int countPairs(int[] deliciousness) {
        int mod = (int) 1e9 + 7;
        long ans = 0;
        int len = deliciousness.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int v = deliciousness[i] + deliciousness[j];
                if (v == lowbit(v)) {
                    ans++;
                }
            }
        }
        return (int)(ans % mod);
    }
    private int lowbit (int n) {
        return n & -n;
    }
}
