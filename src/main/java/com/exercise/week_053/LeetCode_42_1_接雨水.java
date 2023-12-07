package main.java.com.exercise.week_053;

/**
 * @author cl
 * @version 1.0
 * @description： 动态规划构建左高度数组与右高度数组
 * @date 2023/12/6 19:14
 */
public class LeetCode_42_1_接雨水 {
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new LeetCode_42_1_接雨水().trap(height));
    }
}
