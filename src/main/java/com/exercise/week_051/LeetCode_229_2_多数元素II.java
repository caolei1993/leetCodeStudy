package main.java.com.exercise.week_051;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/7 09:49
 */
public class LeetCode_229_2_多数元素II {
    public List<Integer> majorityElement(int[] nums) {
        int ans1 = 0, ans2 = 0;
        int sum1 = 0, sum2 = 0;
        for (int num : nums) {
            if (ans1 == num && sum1 != 0) {
                sum1++;
            } else if (ans2 == num && sum2 != 0) {
                sum2++;
            } else if (sum1 == 0) {
                ans1 = num;
                sum1++;
            } else if (sum2 == 0) {
                ans2 = num;
                sum2++;
            } else {
                sum1--;
                sum2--;
            }
        }

        sum1 = 0;
        sum2 = 2;

        for (int num: nums) {
            if (num == ans1) {
                sum1++;
            } else if (num == ans2) {
                sum2++;
            }
        }
        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        if (sum1 > len/3) {
            list.add(ans1);
        }
        if (sum2 > len/3) {
            list.add(ans2);
        }
        return list;
    }
}
