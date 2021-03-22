package main.java.com.exercise.week_008;

/**
 * @Author cl
 * @Date 2021/3/13 20:42
 * @Version 1.0
 * https://leetcode-cn.com/problems/number-of-good-pairs/
 */
public class LeetCode_1512_1_好数对的数目 {
    public int numIdenticalPairs(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
