package main.java.com.exercise.week_001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2022/4/25 9:25 上午
 */
public class Test_1 {
    int length;
    public List<String> test(String str) {
        length = str.length();
        Set<String> set = new HashSet<>();
        backTrack(str, new StringBuilder(), new boolean[str.length()], set);
        return new ArrayList<>(set);
    }

    private void backTrack(String str, StringBuilder sb, boolean[] memo, Set<String> set) {
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (memo[i]) {
                continue;
            }

            char c = cs[i];
            int len = sb.length();

            sb.append(c);
            memo[i] = true;
            if (sb.length() == length) {
                set.add(sb.toString());
            }

//            if (set.add(sb.toString())) {
//                backTrack(str, sb, memo, set);
//            }
            backTrack(str, sb, memo, set);

            memo[i] = false;
            sb.setLength(len);
        }
    }

    public static void main(String[] args) {
        Test_1 t = new Test_1();
        System.out.println(t.test("abc"));
    }
}