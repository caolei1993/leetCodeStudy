package main.java.com.exercise.week_001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/20 6:26 下午
 */
public class Test_6 {

    public List<Integer> fin(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }
}
