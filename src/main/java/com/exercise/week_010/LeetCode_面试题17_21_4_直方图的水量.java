package main.java.com.exercise.week_010;

/**
 * @Author cl
 * @Date 2021/4/9 15:35
 * @Version 1.0
 */
public class LeetCode_面试题17_21_4_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0, left = 0, right = length - 1, leftMax = 0, rightMax = 0;
        if (length < 3) {
            return ans;
        }
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}
