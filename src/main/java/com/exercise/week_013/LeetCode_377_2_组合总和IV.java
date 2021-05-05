package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/25 17:43
 * @Version 1.0
 */
public class LeetCode_377_2_组合总和IV {
    public int combinationSum4(int[] nums, int target) {

        // f[i]值为i的方案数
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int j = 1; j <= target ; j++) {
            for (int value : nums) {
                if (j >= value) {
                    f[j] += f[j - value];
                }
            }
        }
        return f[target];
    }
}
