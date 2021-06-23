package main.java.com.exercise.week_022;

/**
 * @Author cl
 * @Date 2021/6/23 16:15
 * @Version 1.0
 */
public class LeetCode_剑指Offer15_2_二进制中1的个数 {
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans++;
            n -= lowbit(n);
        }
        return ans;
    }

    private int lowbit(int n) {
        return n & -n;
    }

}
