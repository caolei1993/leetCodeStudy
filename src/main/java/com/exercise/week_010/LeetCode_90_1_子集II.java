package main.java.com.exercise.week_010;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/3/31 19:09
 * @Version 1.0
 * https://leetcode-cn.com/problems/subsets-ii/
 */
public class LeetCode_90_1_子集II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        // 枚举 i 代表，枚举所有的选择方案状态
        // 例如 [1,2]，我们有 []、[1]、[2]、[1,2] 几种方案，分别对应了 00、10、01、11 4种状态
        for (int i = 0; i < (1 << length); i++) {
            list.clear();
            for (int j = 0; j < length; j++) {
                // 对当前状态进行诸位检查，如果当前状态为 1 代表被选择，加入当前方案中
                int t = (i >> j) & 1;
                if (t == 1) {
                    list.add(nums[j]);
                }
            }
            // 使用新的
            ans.add(new ArrayList<>(list));
        }
        return new ArrayList<>(ans);
    }
}
