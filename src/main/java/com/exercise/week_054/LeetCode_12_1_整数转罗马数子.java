package main.java.com.exercise.week_054;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/12 20:35
 */
public class LeetCode_12_1_整数转罗马数子 {
    int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] sys = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder s = new StringBuilder();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (num >= nums[i]) {
                num -= nums[i];
                s.append(sys[i]);
            }
            if (num == 0) {
                break;
            }
        }
        return s.toString();
    }
}
