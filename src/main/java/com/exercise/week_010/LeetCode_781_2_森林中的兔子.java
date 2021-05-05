package main.java.com.exercise.week_010;

import java.util.Arrays;

/**
 * @Author cl
 * @Date 2021/4/4 21:50
 * @Version 1.0
 */
public class LeetCode_781_2_森林中的兔子 {
    public int numRabbits(int[] answers) {
        Arrays.sort(answers);
        int ans = 0;
        int length = answers.length;
        for (int i = 0; i < length ; i++) {
            int k = answers[i];
            ans += k + 1;
            while (k-- > 0 && i + 1 < length && answers[i] == answers[i + 1]) {
                i++;
            }
        }
        return ans;
    }
}
