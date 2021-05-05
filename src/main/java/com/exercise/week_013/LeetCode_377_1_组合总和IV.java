package main.java.com.exercise.week_013;

/**
 * @Author cl
 * @Date 2021/4/24 21:02
 * @Version 1.0
 * https://leetcode-cn.com/problems/combination-sum-iv/
 */
public class LeetCode_377_1_组合总和IV {
    public int combinationSum4(int[] nums, int target) {
        // 因为nums[i]的最小值是1，所以组成target的最大长度为target个1
        int length = target;
        int ans = 0;
        // f[i][j]代表组合中元素个数为i个，值为j的方案数
        int[][] f = new int[length + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j <= target ; j++) {
                for (int value : nums) {
                    if (j >= value) {
                        f[i][j] += f[i - 1][j - value];
                    }
                }
            }
            ans += f[i][target];
        }
        return ans;
    }
}
