package main.java.com.exercise.week_051;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/11/6 20:48
 */
public class LeetCode_169_2_多数元素 {
    public int majorityElement(int[] nums) {
        int ans = 0, vote = 0;
        for (int num : nums) {
            if (vote == 0) {
                ans = num;
            }
            vote += (ans == num ? 1 : -1);
        }
        return ans;
    }
}
