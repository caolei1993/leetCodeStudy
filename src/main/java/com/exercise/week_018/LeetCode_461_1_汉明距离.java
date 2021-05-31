package main.java.com.exercise.week_018;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/27 22:31
 */
public class LeetCode_461_1_汉明距离 {
    public int hammingDistance(int x, int y) {
        int ans = 0;
        for (int i = 0; i < 32 ; i++) {
            int a = x >> i & 1;
            int b = y >> i & 1;
            if (a != b) {
                ans++;
            }
        }
        return ans;
    }
}
