package main.java.com.exercise.week_010;

/**
 * @Author cl
 * @Date 2021/3/29 13:51
 * @Version 1.0
 * https://leetcode-cn.com/problems/reverse-bits/
 */
public class LeetCode_190_1_颠倒二进制位 {
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int t = (n >> i) & 1;
            if (t == 1) {
                ans |= (1 << 31 - i);
            }
        }
        return ans;
    }
}
