package main.java.com.exercise.week_045;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/29 5:04 下午
 */
public class LeetCode_1995_2_统计特殊四元组 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] cnt = new int[310];
        for (int c = n - 2; c >= 2; c--) {
            // 使用数组记录d的值与出现的频率
            cnt[nums[c + 1]]++;
            for (int a = 0; a < n; a++) {
                for (int b = a + 1; b < c; b++) {
                    ans += cnt[nums[a] + nums[b] + nums[c]];
                }
            }
        }
        return ans;
    }
}
