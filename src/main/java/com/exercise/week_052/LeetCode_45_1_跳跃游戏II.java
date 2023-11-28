package main.java.com.exercise.week_052;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/23 20:02
 */
public class LeetCode_45_1_跳跃游戏II {
    public int jump(int[] nums) {
        int len = nums.length;
        int position = len - 1;
        int step = 0;
        while (position > 0) {
            for (int i = 0; i < len; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }
}
