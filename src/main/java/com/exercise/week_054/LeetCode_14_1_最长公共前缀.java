package main.java.com.exercise.week_054;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/13 17:45
 */
public class LeetCode_14_1_最长公共前缀 {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int len = strs.length;

        String str0 = strs[0];
        int len0 = strs[0].length();
        for (int i = 0; i < len0; i++) {
            char c = str0.charAt(i);
            for (int j = 1; j < len; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != c) {
                    return str0.substring(0, i);
                }
            }
        }
        return str0;
    }

}
