package main.java.com.exercise.week_041;

/**
 * @Author cl
 * @Date 2021/11/13 21:15
 * @Version 1.0
 */
public class LeetCode_520_1_检测大写字母 {
    public boolean detectCapitalUse(String word) {
        if (word.toUpperCase().equals(word)) {
            return true;
        }
        if (word.toLowerCase().equals(word)) {
            return true;
        }
        int n = word.length(), idx = 1;
        if (Character.isUpperCase(word.charAt(0))) {
            while (idx < n && Character.isLowerCase(word.charAt(idx))) {
                idx++;
            }
        }
        return idx == n;
    }
}
