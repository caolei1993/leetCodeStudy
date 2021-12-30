package main.java.com.exercise.week_045;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/29 5:03 下午
 */
public class LeetCode_1995_1_统计特殊四元组 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
