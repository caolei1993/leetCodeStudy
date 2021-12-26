package main.java.com.exercise.week_044;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/26 3:49 下午
 */
public class LeetCode_1078_1_Bigram分词 {
    public String[] findOcurrences(String text, String first, String second) {
        String[] strs = text.split(" ");
        int len = strs.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < len - 2; i++) {
            if (strs[i].equals(first) && strs[i + 1].equals(second)) {
                ans.add(strs[i + 2]);
            }
        }
        return ans.toArray(new String[0]);
    }
}
