package main.java.com.exercise.week_001;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/29 10:50 AM
 */
public class Test_7 {
    public static void main(String[] args) {
        Test_7 test = new Test_7();
        System.out.println(test.rearrangeCharacters("abbaccaddaeea", "aaaaa"));
    }
    public int rearrangeCharacters(String s, String target) {
        int[] ins = new int[26];
        char[] chs = s.toCharArray();
        for (char c : chs) {
            ins[c - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        int[] ths = new int[26];
        for (char c : target.toCharArray()) {
            ths[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (ths[i] != 0) {
                ans = Math.min(ans, ins[i] / ths[i]);
            }
        }
        return ans;
    }
}
