package main.java.com.exercise.week_055;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2023/12/19 20:54
 */
public class LeetCode_6_1_N字形变换 {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        int len = s.length();
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int sum = 0, flag = -1;
        for (char c : s.toCharArray()) {
            list.get(sum).append(c);
            if (sum == 0 || sum == numRows - 1) {
                flag = -flag;
            }
            sum += flag;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : list) {
            ans.append(sb);
        }
        return ans.toString();
    }
}
