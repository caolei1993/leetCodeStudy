package main.java.com.exercise.week_017;

/**
 * @author cl
 * @version 1.0
 * @date 2021/5/22 19:30
 */
public class LeetCode_810_1_黑板异或游戏 {
    public boolean xorGame(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum ^= i;
        }
        return sum == 0 || nums.length % 2 == 0;
    }
}
