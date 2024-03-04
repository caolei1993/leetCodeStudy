package main.java.com.exercise.week_061;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/2/5 09:33
 */
public class LeetCode_228_1_汇总区间 {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            String s = "";
            int currentNum = nums[i];
            int index = i;
            while (i + 1 < len && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (i == index) {
                list.add(s + nums[i]);
            } else {
                list.add(s + currentNum + "->" + nums[i]);
            }
        }
        return list;
    }
}
