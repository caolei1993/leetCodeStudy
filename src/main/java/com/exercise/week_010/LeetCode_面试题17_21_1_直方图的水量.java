package main.java.com.exercise.week_010;

/**
 * @Author cl
 * @Date 2021/4/2 9:22
 * @Version 1.0
 * https://leetcode-cn.com/problems/volume-of-histogram-lcci/
 */
public class LeetCode_面试题17_21_1_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0;
        if (length < 3) {
            return ans;
        }
        for (int i = 1; i < length - 1; i++) {
            int cur = height[i];
            int lHeight = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                lHeight = Math.max(lHeight, height[j]);
            }
            if (cur >= lHeight) {
                continue;
            }
            int rHeight = Integer.MIN_VALUE;
            for (int j = i+1; j < length; j++) {
                rHeight = Math.max(rHeight, height[j]);
            }
            if (cur >= rHeight) {
                continue;
            }
            ans += Math.min(lHeight, rHeight) - cur;
        }
        return ans;
    }
}
