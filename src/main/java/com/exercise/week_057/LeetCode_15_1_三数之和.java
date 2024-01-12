package main.java.com.exercise.week_057;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/9 18:59
 */
public class LeetCode_15_1_三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        // 数组排序
        Arrays.sort(nums);
        // 第一个元素遍历
        for (int i = 0; i < n - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = n - 1;
            // 遍历第二个元素
            for (int j = i + 1; j < n - 1; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (k > j && nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }
                if (k != j && nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode_15_1_三数之和().threeSum(new int[]{1,2,-2,-1}));
    }
}
