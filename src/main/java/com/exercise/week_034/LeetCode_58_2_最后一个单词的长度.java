package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/21 19:48
 * @Version 1.0
 */
public class LeetCode_58_2_最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int j = n - 1;
        while (j >= 0 && s.charAt(j) == ' ') {
            j--;
        }
        int i = j;
        while (i >= 0 && s.charAt(i) != ' ') {
            i--;
        }
        return j - i;
    }
}
