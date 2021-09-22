package main.java.com.exercise.week_034;

/**
 * @Author cl
 * @Date 2021/9/21 19:44
 * @Version 1.0
 */
public class LeetCode_58_1_最后一个单词的长度 {
    public int lengthOfLastWord(String s) {
        String[] arr = s.split(" ");
        int n = arr.length;
        return arr[n - 1].length();
    }
}
