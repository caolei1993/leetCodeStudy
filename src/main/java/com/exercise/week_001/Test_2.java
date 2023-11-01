package main.java.com.exercise.week_001;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/5/1 12:25 下午
 */
class Test_2 {

    public long appealSum(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String str = s.substring(i, j);
                ans += count(str);
                System.out.println(str);
            }
        }
        return ans;
    }

    private int count(String str) {
        char[] chs = str.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chs) {
            set.add(c);
        }
        return set.size();
    }

    public static void main(String[] args) {
        Test_2 test = new Test_2();
        System.out.println(test.appealSum("abbca"));
    }
}