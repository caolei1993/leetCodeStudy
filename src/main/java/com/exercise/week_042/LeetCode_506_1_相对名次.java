package main.java.com.exercise.week_042;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cl
 * @version 1.0
 * @date 2021/12/2 8:32 下午
 */
public class LeetCode_506_1_相对名次 {
    String[] str = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[] clone = score.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0 ; i--) {
            map.put(clone[i], n - 1 - i);
        }

        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            Integer val = map.get(score[i]);
            ans[i] = val < 3 ? str[val] : String.valueOf(val + 1);
        }

        return ans;
    }
}
