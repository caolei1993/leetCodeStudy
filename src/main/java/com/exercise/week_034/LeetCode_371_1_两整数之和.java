package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/26 9:26
 * @Version 1.0
 */
public class LeetCode_371_1_两整数之和 {
    public int getSum(int a, int b) {
        int ans = 0, t = 0;
        for (int i = 0; i < 32; i++) {
            int u1 = (a >> i) & 1, u2 = (b >> i) & 1;
            if (u1 == 1 && u2 == 1) {
                ans |= (t << i);
                t = 1;
            } else if (u1 == 1 || u2 == 1) {
                ans |= (t ^ 1) << i;
            } else {
                ans |= (t << i);
                t = 0;
            }
        }
        return ans;
    }
}
