package main.java.com.exercise.week_049;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/2/23 2:44 下午
 */
public class LeetCode_917_1_仅仅反转字母 {
    public String reverseOnlyLetters(String s) {
        char[] cs = s.toCharArray();
        int l = 0, r = cs.length - 1;
        while (l < r) {
            // 从左到右查询字母
            while (!Character.isLetter(cs[l]) && l < r) {
                l++;
            }
            // 从右到左查询字母
            while (!Character.isLetter(cs[r]) && r > l) {
                r--;
            }
            if (l < r) {
                char mid = cs[r];
                cs[r--] = cs[l];
                cs[l++] = mid;
            }
        }
        return String.valueOf(cs);
    }
}
