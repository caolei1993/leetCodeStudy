package main.java.com.exercise.week_020;

/**
 * @Author cl
 * @Date 2021/6/7 20:34
 * @Version 1.0
 */
public class LeetCode_494_1_目标和 {
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }

    /**
     *
     * @param nums  目标数组
     * @param target  目标值
     * @param i 当前遍历的长度
     * @param cur 当前的计算值
     * @return
     */
    private int dfs(int[] nums, int target, int i, int cur) {
        if (i == nums.length) {
            return cur == target ? 1 : 0;
        }
        int left = dfs(nums, target, i + 1, cur + nums[i]);
        int right = dfs(nums, target, i + 1, cur - nums[i]);
        return left + right;
    }


}
