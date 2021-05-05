package main.java.com.exercise.week_010;

/**
 * @Author cl
 * @Date 2021/4/5 13:47
 * @Version 1.0
 */
public class LeetCode_面试题17_21_2_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0;
        // 小于三个柱状图无法蓄水
        if (length < 3) {
            return ans;
        }
        // 创建左侧最大值数组，并初始化
        int[] leftMax = new int[length];
        leftMax[0] = height[0];
        for (int i = 1; i < length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 创建右侧最大值数组，并初始化
        int[] rightMax = new int[length];
        rightMax[length - 1] = height[length -1];
        for (int i = length - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 首位置和最后一个位置不可能蓄水，所以只需遍历首尾之前的值
        for (int i = 1; i < length - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}
