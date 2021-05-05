package main.java.com.exercise.week_012;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/4/12 9:17
 * @Version 1.0
 * https://leetcode-cn.com/problems/largest-number/
 */
public class LeetCode_179_1_最大数 {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = nums[i] + "";
        }
        Arrays.sort(strArr, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder builder = new StringBuilder();
        for(String str : strArr) {
            builder.append(str);
        }
        String ans = builder.toString();
        return ans.charAt(0) == '0' ? "0" : ans;
    }
}
