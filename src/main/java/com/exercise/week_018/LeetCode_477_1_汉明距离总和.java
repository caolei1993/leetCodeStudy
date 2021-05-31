package main.java.com.exercise.week_018;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/28 21:04
 */
public class LeetCode_477_1_汉明距离总和 {
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int s0 = 0, s1 = 0;
            for (int num : nums) {
                int a = num >> i & 1;
                if (a == 0) {
                    s0++;
                } else {
                    s1++;
                }
            }
            ans += s0 * s1;
        }
        return ans;
    }
}
