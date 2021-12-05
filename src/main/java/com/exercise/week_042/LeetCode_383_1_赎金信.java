package main.java.com.exercise.week_042;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/5 8:19 上午
 */
public class LeetCode_383_1_赎金信 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char cc : ransomNote.toCharArray()) {
            if (--cnt[cc - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
