package main.java.com.exercise.week_029;

/**
 * @Author cl
 * @Date 2021/8/10 11:10
 * @Version 1.0
 */
public class LeetCode_413_1_等差数列划分 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        // 遍历子数组左边界
        for (int i = 0; i < n - 2 ;) {
            int j = i, d = nums[i + 1] - nums[i];
            while (j + 1 < n && nums[j + 1] - nums[j] == d) {
                j++;
            }
            int len = j - i + 1;
            int a1 = 1, an = len - 3 + 1;
            ans += (a1 + an) * an / 2;
            i = j;
        }
        return ans;
    }
}
